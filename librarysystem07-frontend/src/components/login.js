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
    this.demeritPoints = 0;
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
            libraryCardId: 0,
            demeritPoints: 0
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
          signIn: function (username, libraryCardId) {
            if (visitors.get(libraryCardId).username == username) {
                router.push("/visitors/".concat(libraryCardId).concat("/mainpage"))
            }
          },

          createVisitor: function (name, username, address, libraryCardId) {
            var indexName = this.visitors.map(x => x.name).indexOf(name)
            var indexUsername = this.visitors.map(x => x.username).indexOf(username)
            var indexAddress = this.visitors.map(x => x.address).indexOf(address)
            var indexLibraryCardId = this.visitors.map(x => x.libraryCardId).indexOf(libraryCardId)

            var visitor = this.visitors[indexLibraryCardId]

            AXIOS.post('/visitors'.concat(libraryCardId), {},
              {params: {
                name: visitor.name,
                username: visitor.username,
                address: visitor.address,
                demeritPoints: visitor.demeritPoints
            } })
            .then(response => {
              // Update appropriate DTO collections
                newVisitor.name.push(name)
                newVisitor.username.push(username)
                newVisitor.address.push(address)
                newVisitor.libraryCardId.push(libraryCardId)
                newVisitor.demeritPoints.push(demeritPoints)
                this.visitors.push(response.data)
                this.errorVisitor = ''
            })
            .catch(e => {
              var errorMsg = e
              console.log(errorMsg)
              this.errorVisitor = errorMsg
            })
          },
      }
  }
