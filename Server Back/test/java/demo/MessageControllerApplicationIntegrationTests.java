//package demo;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//import java.util.stream.Stream;
//
//import javax.annotation.PostConstruct;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.web.client.RestTemplate;
//
//import demo.logic.MessageService;
//import demo.rest.boundaries.MessageBoundary;
//import demo.rest.boundaries.MessageType;
//
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//public class MessageControllerApplicationIntegrationTests {
//	private int port;
//	private String baseUrl;
//	private RestTemplate restTemple;
//	
//	private MessageService messageService;
//	
//	@LocalServerPort
//	public void setPort(int port) {
//		this.port = port;
//	}
//	
//	@Autowired
//	public void setMessageService(MessageService messageService) {
//		this.messageService = messageService;
//	}
//	
//	@PostConstruct
//	public void init() {
//		this.baseUrl = "http://localhost:" + port;
//		this.restTemple = new RestTemplate();
//	}
//	
//	@AfterEach
//	@BeforeEach
//	public void teardown() {
//		this.messageService.deleteAll();
//	}
//	
//	@Test
//	public void testGetHelloReturnsTheOnlyObjectIfAnyExistsInDb() throws Exception{
//		// GIVEN the database contains a single message
//		MessageBoundary singleMessage = new MessageBoundary(null, true, MessageType.text, Collections.singletonMap("text", "hello test"), null);
//		singleMessage = this.messageService.createMessage(singleMessage);
//		
//		// WHEN we invoke GET /hello 
//		MessageBoundary actual = 
//			this.restTemple.getForObject(
//				this.baseUrl + "/hello", 
//				MessageBoundary.class);
//		
//		// THEN the single message is returned
////		assertThat(actual).isNotNull();
////		assertThat(actual.getId()).isEqualTo(singleMessage.getId());
//		
//		assertThat(actual)
//			.isNotNull()
//			//.extracting("id")
//			//.isEqualTo(singleMessage.getId());
//			.usingRecursiveComparison()
//			.isEqualTo(singleMessage);
//	}
//	
//	@Test
//	public void testGetHelloReturnsFirstObjectIfAnyExistsInDb() throws Exception{
//		// GIVEN the database contains 5 messages
//		MessageBoundary first = 
//		  IntStream.range(1, 6)
//			.mapToObj(i->new MessageBoundary(null, true, MessageType.text, Collections.singletonMap("text", "hello test #" + i), null))
//			.map(this.messageService::createMessage)
//			.findFirst()
//			.orElseThrow(()->new RuntimeException("error while initializing test"));
//		
//		// WHEN we invoke GET /hello 
//		MessageBoundary actual = 
//				this.restTemple.getForObject(
//					this.baseUrl + "/hello", 
//					MessageBoundary.class);
//			
//		// THEN the first message, ordered by id, is returned
////		assertThat(actual).isNotNull();
////		assertThat(actual.getId()).isEqualTo(first.getId());
//		
////		assertThat(actual)
////			.isNotNull()
////			.extracting("id")
////			.isEqualTo(first.getId());
//		
//		assertThat(actual)
//			.isNotNull()
//			.isEqualToComparingOnlyGivenFields(first, "id", "important", "type");
//	}
//	
//	@Test
//	public void testGetAllReturnsAllObjectInDb() throws Exception{
//		// GIVEN the database contains 5 messages
//		List<MessageBoundary> allInDb  = 
//		  IntStream.range(1, 6)
//			.mapToObj(i->new MessageBoundary(null, true, MessageType.text, Collections.singletonMap("text", "hello test #" + i), null))
//			.map(this.messageService::createMessage)
//			.collect(Collectors.toList());
//		
//		// WHEN we invoke GET /all 
//		MessageBoundary[] actualArray = 
//				this.restTemple.getForObject(
//					this.baseUrl + "/all", 
//					MessageBoundary[].class);
//		List<MessageBoundary> actualList = Stream.of(actualArray).collect(Collectors.toList());
//		
//		// THEN the all messages are returned in any order
//		assertThat(actualList)
//			.usingElementComparatorOnFields("id")
//			.containsAll(allInDb);
//
//	}
//
//	@Test
//	public void testGetByIdReturnsObjectFromDb() throws Exception{
//		// GIVEN the database contains 5 messages
//		List<MessageBoundary> allInDb  = 
//		  IntStream.range(1, 6)
//			.mapToObj(i->new MessageBoundary(null, true, MessageType.text, Collections.singletonMap("text", "hello test #" + i), null))
//			.map(this.messageService::createMessage)
//			.collect(Collectors.toList());
//		
//		MessageBoundary last = allInDb.get(allInDb.size() - 1);
//		
//		// WHEN we invoke GET /hello/{lastId} 
//		MessageBoundary actual = 
//				this.restTemple.getForObject(
//					this.baseUrl + "/hello/{id}", 
//					MessageBoundary.class,
//					last.getId());
//		
//		// THEN the all messages are returned in any order
//		assertThat(actual)
////			.isNotNull()
//			.isEqualToComparingFieldByField(last);
//
//	}
//
//	@Test
//	public void testPostNewMessageIsSuccessful() throws Exception{
//		// GIVEN the database is clean
//		
//		// WHEN I POST a new message
//		MessageBoundary newMessageBoundary = new MessageBoundary(null, true, MessageType.image, Collections.singletonMap("file", "test.mp4"), null);
//		
//		this.restTemple
//			.postForObject(
//					this.baseUrl + "/hello", 
//					newMessageBoundary, 
//					MessageBoundary.class);
//		
//		// THEN the database contains the new message
//		assertThat(this.messageService.getAllMessages())
//			.isNotEmpty()
//			.usingElementComparatorOnFields("important", "type", "content")
//			.containsExactly(newMessageBoundary);
//	}
//	
//	@Test
//	public void testPutMessageTypeIsSuccessfull() throws Exception{
//		// GIVEN the database contains a message with type video
//		MessageBoundary oldMessage = 
//		  this.messageService
//			.createMessage(new MessageBoundary(null, null, MessageType.video, null, null));
//			
//		// WHEN we PUT /hello/{id} and we send an update of the type
//		MessageType newType = MessageType.image;
//		this.restTemple
//			.put(this.baseUrl + "/hello/{id}", 
//				Collections.singletonMap("type", newType), 
//				oldMessage.getId());
//		
//		
//		// THEN the database is updated with the new type
//		assertThat(this.messageService.getMessageById(oldMessage.getId()))
//			.extracting("id", "type")
////			.isEqualTo(newType);
//			.containsExactly(oldMessage.getId(), newType);
//	}
//	
//	@Test
//	public void testDeleteByIdActuallyDeletedAMessages() throws Exception{
//		// GIVEN database with 5 messages
//		List<MessageBoundary> allInDb  = 
//				  IntStream.range(1, 6)
//					.mapToObj(i->new MessageBoundary(null, true, MessageType.text, Collections.singletonMap("text", "hello test #" + i), null))
//					.map(this.messageService::createMessage)
//					.collect(Collectors.toList());
//				
//		// WHEN we DELETE /hello/{id}
//		Collections.shuffle(allInDb);
//		MessageBoundary forDeletion = allInDb.get(0);
//		this.restTemple.delete(
//				this.baseUrl + "/hello/{id}", 
//				forDeletion.getId());
//		
//		// THEN the database does not contain the deleted message any more
//		assertThat(this.messageService.getAllMessages())
//			.usingElementComparatorOnFields("id")
//			.doesNotContain(forDeletion);
//	}
//	
//	@Test
//	public void testGetNonExistingFails() throws Exception{
//		// GIVEN the database is empty
//		
//		// WHEN we GET /hello/{id} with any id
//		// THEN an exception occurs
//		assertThrows(Exception.class, ()->
//			this.restTemple.getForObject(
//					this.baseUrl + "/hello/{id}", 
//					MessageBoundary.class, 
//					"5"));
//		
//	}
//	
//	@Test
//	public void testGetNonExistingFailsWithSomeDataInitialized() throws Exception{
//		// GIVEN the database is not empty and then it becomes empty
//		String deletedId =
//				this.messageService.createMessage(new MessageBoundary(null, true, MessageType.text, Collections.singletonMap("text", "test"), null))
//				.getId();
//		this.messageService
//			.deleteAll();
//		
//		// WHEN we GET /hello/{id} with id that used to exist in db
//		// THEN an exception occurs
//		assertThrows(Exception.class, ()->
//			this.restTemple.getForObject(
//					this.baseUrl + "/hello/{id}", 
//					MessageBoundary.class, 
//					deletedId));
//		
//	}
//	
//	@Test
//	public void testParentDetailsUsingBL(){
//		MessageBoundary parent = 
//		this.messageService
//			.createMessage(new MessageBoundary(null, true, MessageType.text, new HashMap<>(), null));
//
//		MessageBoundary child = 
//				this.messageService
//					.createMessage(new MessageBoundary(null, true, MessageType.text, new HashMap<>(), null, parent.getId()));
//				
//		MessageBoundary childFromService = 
//				this.messageService
//					.getMessageById(child.getId());
//		
//		assertThat(childFromService.getParent())
//			.isNotNull()
//			.isEqualTo(parent.getId());
//	}
//	
//	@Test
//	public void testParentDetails(){
//		MessageBoundary parent = 
//				this.restTemple
//					.postForObject(this.baseUrl + "/hello", new MessageBoundary(null, true, MessageType.text, new HashMap<>(), null), MessageBoundary.class);
//
//		MessageBoundary child = 
//				this.restTemple
//					.postForObject(this.baseUrl + "/hello", new MessageBoundary(null, true, MessageType.text, new HashMap<>(), null, parent.getId()), MessageBoundary.class);
//				
//		MessageBoundary childFromService = 
//				this.restTemple
//					.getForObject(this.baseUrl + "/hello/{id}", MessageBoundary.class, child.getId());
//		
//		assertThat(childFromService.getParent())
//			.isNotNull()
//			.isEqualTo(parent.getId());
//	}
//
//	@Test
//	public void testChildDetails(){
//		MessageBoundary parent = 
//				this.restTemple
//					.postForObject(this.baseUrl + "/hello", new MessageBoundary(null, true, MessageType.text, new HashMap<>(), null), MessageBoundary.class);
//
//		MessageBoundary child = 
//				this.restTemple
//					.postForObject(this.baseUrl + "/hello", new MessageBoundary(null, true, MessageType.text, new HashMap<>(), null, parent.getId()), MessageBoundary.class);
//				
//		MessageBoundary parentFromService = 
//				this.restTemple
//					.getForObject(this.baseUrl + "/hello/{id}", MessageBoundary.class, parent.getId());
//		
//		assertThat(parentFromService.getChildrenIds())
//			.isNotEmpty()
//			.hasSize(1)
//			.containsExactly(child.getId());
//	}
//
//	@Test
//	public void testChildrenDetails(){
//		MessageBoundary parent = 
//				this.restTemple
//					.postForObject(this.baseUrl + "/hello", new MessageBoundary(null, true, MessageType.text, new HashMap<>(), null), MessageBoundary.class);
//
//		List<String> childrenIds = 
//		IntStream.range(0, 10)
//			.mapToObj(i->this.restTemple
//					.postForObject(this.baseUrl + "/hello", new MessageBoundary(null, true, MessageType.text, new HashMap<>(), null, parent.getId()), MessageBoundary.class))
//			.map(MessageBoundary::getId)
//			.collect(Collectors.toList());
//				
//		MessageBoundary parentFromService = 
//				this.restTemple
//					.getForObject(this.baseUrl + "/hello/{id}", MessageBoundary.class, parent.getId());
//		
//		assertThat(parentFromService.getChildrenIds())
//			.isNotEmpty()
//			.containsExactlyInAnyOrderElementsOf(childrenIds);
//	}
//
//}
//
//
//
//
//
