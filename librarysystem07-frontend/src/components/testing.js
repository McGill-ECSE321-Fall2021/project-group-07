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
    this.demeritPoints = 0;
    if (address.includes("Montreal") || address.includes("montreal")) {
        this.balance = 0;
    }
    else {
        this.balance = 15;
    }
}

export default {
    name: 'visitor',
    data () {
      return {
          visitors: [],
          visitorIds: [],
          visitorUsernames: [],
            newVisitor: {
                name: '',
                username: '',
                address: '',
                libraryCardId: '',
                demeritPoints: 0,
                balance: 0
            },
            // newVisitor: '',
            errorVisitor: '',
            errorNewVisitor: '',
            response: []
        }
    },

    created: function () {
        AXIOS.get('/visitors')
        .then(response => {
          // JSON responses are automatically parsed.
          this.persons = response.data
        })
        //TEST DATA
        const v1 = new VisitorDto("John", "John1", "Montreal", 0);
        const v2 = new VisitorDto("Bob", "Bob1", "Montreal", 1);

        this.visitors = [v1, v2]
        this.visitorIds = [
            {libraryCardId: v1.libraryCardId}, 
            {libraryCardId: v2.libraryCardId}
        ]
        this.visitorUsernames = [
            {libraryCardId: v1.username}, 
            {libraryCardId: v2.username}
        ]

    //     // Initializing persons from backend
    //     AXIOS.get('/visitors')
    //     .then(response => {
    //         // JSON responses are automatically parsed.
    //         this.visitors = response.data
    //     })
    //     .catch(e => {
    //         this.errorVisitor = e
    //     })
       },

      methods: {
        createVisitor: function (visitorName, visitorUsername, visitorAddress, visitorLibraryCardId) {
            
            // Create a new person and add it to the list of people
            var v = new VisitorDto(visitorName, visitorUsername, visitorAddress, visitorLibraryCardId)
            this.visitors.push(v)
            this.visitorIds.push(visitorLibraryCardId);
            this.visitorUsernames.push(visitorUsername);
            // Reset the name field for new people
            this.newVisitor.username = ''
            this.newVisitor.name = ''
            this.newVisitor.address = ''
            this.newVisitor.libraryCardId = ''
          },

          signIn: function (visitorUsername, visitorLibraryCardId) {
            for (id in visitorIds) {
                this.errorVisitor = id;

                if (id == visitorLibraryCardId) {
                }
            }

            // let indexId = visitors.indexOf(id);
            // let username = visitorUsernames.find(x => x.username === visitorUsername);
            // let indexUsername = visitorUsernames.indexOf(username);
            // if (indexId == indexUsername) {
            //     this.$router.push('/app'); 
            // }
            // else {
            //     this.errorVisitor = "nope";
            // }
            // if (this.visitors.includes(visitorLibraryCardId)) {
            //     var indexOfVisitor = this.visitors.map(x => x.libraryCardId).indexOf(visitorLibraryCardId)
                // if (this.visitorUsernames[indexOfVisitor] == visitorUsername) {
                //     this.$router.push('/app'); 
                // }
            // }
            // else {
            //     this.errorVisitor = "Username and ID do not match.";
            // }
          }
        }


    //       signIn: function (username, libraryCardId) {
    //         if (visitors.get(libraryCardId).username == username) {
    //             router.push("/visitors/".concat(libraryCardId).concat("/mainpage"))
    //         }
    //       },

    //       createVisitor: function (name, username, address, libraryCardId) {
    //         var indexName = this.visitors.map(x => x.name).indexOf(name)
    //         var indexUsername = this.visitors.map(x => x.username).indexOf(username)
    //         var indexAddress = this.visitors.map(x => x.address).indexOf(address)
    //         var indexLibraryCardId = this.visitors.map(x => x.libraryCardId).indexOf(libraryCardId)

    //         var visitor = this.visitors[indexLibraryCardId]

    //         AXIOS.post('/visitors'.concat(libraryCardId), {},
    //           {params: {
    //             name: visitor.name,
    //             username: visitor.username,
    //             address: visitor.address,
    //             demeritPoints: visitor.demeritPoints
    //         } })
    //         .then(response => {
    //           // Update appropriate DTO collections
    //             newVisitor.name.push(name)
    //             newVisitor.username.push(username)
    //             newVisitor.address.push(address)
    //             newVisitor.libraryCardId.push(libraryCardId)
    //             newVisitor.demeritPoints.push(demeritPoints)
    //             this.visitors.push(response.data)
    //             this.errorVisitor = ''
    //         })
    //         .catch(e => {
    //           var errorMsg = e
    //           console.log(errorMsg)
    //           this.errorVisitor = errorMsg
    //         })
    //       },
    //   }
  
}
