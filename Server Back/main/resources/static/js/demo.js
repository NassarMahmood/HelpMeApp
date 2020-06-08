window.onload = function () {
	var app = new Vue({
	  el: '#app',
	  data: {
	    messages: [],
	    messageCount: 0,
	    page : 0,
	    size :2,
	    errorMessage : null
	  },
	  methods: {
		  loadMessages: function () {
			  this.errorMessage = null;
			  axios
			  	.get('/all?size=' + this.size + '&page=' + this.page)
			  	.then(response => {
			  		this.messages = response.data;
			  		this.messageCount = this.messages.length;
			  	}).catch(error => {
			  		console.log("error: " + error);
			  		this.errorMessage = "Error while retrieving data: " + error;
			  	});
		  },
		  
		  loadNextPage : function(){
			  this.page++;
			  this.loadMessages();
		  },
		  
		  loadFirstPage : function (){
			  this.page = 0;
			  this.loadMessages();
		  }
		   
	  }
	})
}