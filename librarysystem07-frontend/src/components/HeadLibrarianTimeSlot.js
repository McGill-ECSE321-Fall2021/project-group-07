import axios, { Axios } from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function LibrarianDTO (name,username,address,libraryCardID){
    this.name = name;
    this.username = username;
    this.address = address;
    this.libraryCardID = libraryCardID;
}

function LibrarianTimeSlotDto (librarianTimeSlotId,startTime,endTime,aDayOfTheWeek,librarian){
    this.librarianTimeSlotId = librarianTimeSlotId;
    this.startTime = startTime;
    this.endTime = endTime;
    this.aDayOfTheWeek = aDayOfTheWeek;
    this.librarian = librarian
}

export default {
	name: 'librariantimeslot',
	data() {
		return{
            librarianTimeSlotId:'',
            librarian: '',
            newLibrarian: '',
			aDayOfTheWeek:'',
			startTime:'',
			endTime:'',

            librarianTimeSlots:[],
            newLibrarianTimeSlot:'',
			deleteLibrarianTimeSlot:'',

			errorLibrarianTimeSlot:'',
			response:[]
		}
	},
	
	created: function (){
        AXIOS.get('/librariantimeslots')
        .then(response => {
        	this.librariantimeslots = response.data;
        })
        .catch(e => {
        this.errorLibrarianTimeSlot = e;
        })
		

		const l1 = new LibrarianDTO("Liamo","liamopen","123 McDonald",123);
		const l2 = new LibrarianDTO("Sam","sabhan","124 McDonald",124);
        const l3 = new LibrarianDTO("Reb","rebz","125 McDonald",125);
		const l4 = new LibrarianDTO("Amelie","ameliebar","126 McDonald",126);
        const l5 = new LibrarianDTO("Basel","basel","127 McDonald",127);

        const ls1 = new LibrarianTimeSlotDto(1,'10:30:00','16:30:00','Monday',l1);
        const ls2 = new LibrarianTimeSlotDto(2,'10:30:00','16:30:00','Tuesday',l2)
        const ls3 = new LibrarianTimeSlotDto(3,'10:30:00','16:30:00','Wednesday',l3)
        const ls4 = new LibrarianTimeSlotDto(4,'10:30:00','16:30:00','Thursday',l4)
        const ls5 = new LibrarianTimeSlotDto(5,'10:30:00','16:30:00','Friday',l5)

		// Sample initial content
		this.librarianTimeSlots = [ls1,ls2,ls3,ls4,ls5];
	},
	
	methods: {

		createLibrarianTimeSLot: function(librarianTimeSlotId,startTime,endTime,aDayOfTheWeek,librarian) {
			AXIOS.post('/librariantimeslots/'.concat(librarianTimeSlotId), {}, 
			{params:{
				librarianTimeSlotId: librarianTimeSlotId,
                librarian: librarian,
			    aDayOfTheWeek: aDayOfTheWeek,
			    startTime: startTime,
			    endTime: endTime
			}})
			
			.then(response => {
				//add new slot to list to be displayed
				this.librarianTimeSlots.push(response.data)
				//reset fields to empty
				this.librarianTimeSlotId = '';
                this.startTime = '';
                this.endTime = '';
                this.aDayOfTheWeek = '';
                this.librarian =  '';
				})
				.catch(e => {
					var errorMsg = e.response.data.message
					console.log(errorMsg)
					this.errorLibrarianTimeSlot = errorMsg
					})
			
			this.newLibrarianTimeSlot = '';
			},
		
	    updateLibrarianTimeSlot: function(librarianTimeSlotId,startTime,endTime,aDayOfTheWeek,librarian){
			AXIOS.patch('/librariantimeslots/'.concat(librarianTimeSlotId), {},
            {params:{
				librarianTimeSlotId: librarianTimeSlotId,
                librarian: librarian,
			    aDayOfTheWeek: aDayOfTheWeek,
			    startTime: startTime,
			    endTime: endTime
			}})

			.then(response => {
				AXIOS.get('/librariantimeslots')
					.then(response =>{
					this.librariantimeslots = response.data
					})
					.catch(e => {
					this.errorLibrarianTimeSlot = e
					})
					this.librarianTimeSlotId = '';
                    this.startTime = '';
                    this.endTime = '';
                    this.aDayOfTheWeek = '';
                    this.librarian =  '';
				})
				.catch(e =>{
					var errorMsg = e.response.data.message
            		console.log(errorMsg)
            		this.errorLibrarianTimeSlot = errorMsg
				})
		    },
		
		deleteLibrarianTimeSlot: function(deleteLibrarianTimeSlot){
			AXIOS.delete('/librariantimeslots/'.concat(deleteLibrarianTimeSlot), {}, {})
				.then(response => {
					AXIOS.get('/librariantimeslots')
					.then(response => {
						this.librarianTimeSlots = response.data
						this.librarianTimeSlotId = '';
                        this.startTime = '';
                        this.endTime = '';
                        this.aDayOfTheWeek = '';
                        this.librarian =  '';
                        this.deleteLibrarianTimeSlot = '';
					})
					.catch(e =>{
					this.errorLibrarianTimeSlot = e
					})
				})
				.catch(e => {
				this.errorLibrarianTimeSlot=e
        	})
		}
	}
}