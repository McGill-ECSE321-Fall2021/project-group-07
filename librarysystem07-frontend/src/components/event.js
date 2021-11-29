import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function EventDto (name, eventId, visitor){
    this.name=name;
    this.eventId=eventId;
    this.visitor=visitor;
}

function VisitorDto(name, username, address, libraryCardId) {
    this.name = name;
    this.username = username;
    this.address = address;
    this.libraryCardId = libraryCardId;
    this.events = [];
    this.reservations = [];
    this.demeritPoints = 0;
    if (address.includes("Montreal") || address.includes("montreal")) {
        this.balance = 0;
    }
    else {
        this.balance = 15;
    }
}

export default {
    name: 'librarysystem07',
    data () {
      return {
          newEvent: {
              name:'',
              eventId: 0,
              visitor: null,
            //   errorEvent: '',
            //   events: [],
            //   response: []
          },
          errorEvent: '',
          events: [],
          response: []
        }
    },
          //errorEvent: '',
          //reservations: [],
          //events: [],
          //response: []
        
      //}
    //},

    created: function () {
        const v1 = new VisitorDto("John", "John1", "Montreal", "0");
        const v2 = new EventDto("book signing", "0" , v1 );

        this.visitors = [v1];
        this.visitorIds = [{libraryCardId: v1.libraryCardId}]
        this.visitorUsernames = [{username: v1.username}]
        
        this.events = [v2];
        this.eventIds = [{eventId: v2.eventId}]
        this.eventNames = [{name: v2.name}]  
        
        // Initializing events from backend
        AXIOS.get('/events')
        .then(response => {
            // JSON responses are automatically parsed.
            this.events = response.data
        })
        .catch(e => {
            this.errorEvent = e
        })
      },

    methods: {
        // clearFields: function ClearFields() {

        //     document.getElementById("textfield1").value = "";
        //     document.getElementById("textfield2").value = "";
        // },
       
        makeEvent: function (nameE) {//, eventId, visitor) {
            //var indexName = this.events.map(x => x.name).indexOf(name)
            //var indexEventId = this.events.map(x => x.eventId).indexOf(eventId)
            //var indexVisitor = this.events.map(x => x.visitor).indexOf(visitor)

            //var event = this.events[indexName]

            // AXIOS.post('/events'.concat(eventId), {},
            //   {params: {
            //     name: event.name,
            //     eventId: event.eventId,
            //     visitor: event.visitor,
            // } })
            AXIOS.post('/events'.concat(nameE), {},
              //  {params: {
              //      event: event.name,
              //      name: nameE,
            //     eventId: event.eventId, //generate random ID?
            //     visitor: event.visitor, //smtg like get visitor?
            //} 
          {})
            .then(response => {
                // Update appropriate DTO collections  
                //newEvent.name.push(nameE)           !!!!
                //newEvent.eventId.push(eventId)
                //newEvent.visitor.push(visitor)
                //this.events.push({nameE : response.data.name}) //was eventName : response.data.name   !!!
                this.events.push({nameE : response.data.name}) 
                this.errorEvent = ''
                this.newEvent =''
              })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorEvent = errorMsg
              })

        },

    }
  }