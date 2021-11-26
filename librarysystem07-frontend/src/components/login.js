import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function VisitorDto (name, username, address, libraryCardId) {
    this.name = name;
    this.username = username;
    this.address = address;
    this.libraryCardId = libraryCardId;
    this.events = [];
    this.reservations = [];
    if (address.includes("Montreal") || address.includes("montreal")) {
        this.balance = 0;
    }
    else {
        this.balance = 15;
    }
}

export default {
    name: 'eventregistration',
    data () {
      return {
        newVisitor: {
            name: '',
            username: '',
            address: '',
            libraryCardId: 0
        },
        errorVisitor: '',
        events: [],
        reservations: [],
        errorVisitor: '',
        visitors: [],
        response: []
      }
    },
    created: function () {
        
        // Initializing persons from backend
        AXIOS.get('/visitors')
        .then(response => {
            // JSON responses are automatically parsed.
            this.visitors = response.data
        })
        .catch(e => {
            this.errorVisitor = e
        })
      },

      methods: {
        createPerson: function (personName) {
            AXIOS.post('/persons/'.concat(personName), {}, {})
              .then(response => {
              // JSON responses are automatically parsed.
                this.persons.push(response.data)
                this.errorPerson = ''
                this.newPerson = ''
              })
              .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorPerson = errorMsg
              })
          },

          registerEvent: function (personName, eventName) {
            var indexEv = this.events.map(x => x.name).indexOf(eventName)
            var indexPart = this.persons.map(x => x.name).indexOf(personName)
            var person = this.persons[indexPart]
            var event = this.events[indexEv]
            AXIOS.post('/register', {},
              {params: {
                person: person.name,
                event: event.name}})
            .then(response => {
              // Update appropriate DTO collections
              person.events.push(event)
              this.selectedPerson = ''
              this.selectedEvent = ''
              this.errorRegistration = ''
            })
            .catch(e => {
              var errorMsg = e
              console.log(errorMsg)
              this.errorRegistration = errorMsg
            })
          },
      }
  }
