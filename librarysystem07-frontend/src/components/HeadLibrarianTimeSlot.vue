<template>
    <div id="headLibrarianTimeSlot">
        <h2>Librarian Time Slot</h2>
		<h4>Exisiting Librarian Time Slots:</h4>
         <table align="center">
            <tr>
                <button v-bind:disabled="(!librarianTimeSlotId||!aDayOfTheWeek||!startTime||!endTime||!librarian)" @click="createLibrarianTimeSLot(librarianTimeSlotId,startTime,endTime,aDayOfTheWeek,librarian)">Add Librarian Time Slot</button>
                <button v-bind:disabled="(!librarianTimeSlotId||!aDayOfTheWeek||!startTime||!endTime||!librarian)" @click="updateLibrarianTimeSlot(librarianTimeSlotId,startTime,endTime,aDayOfTheWeek,librarian)">Update Librarian Time Slot</button>
                <button v-bind:disabled="(!deleteLibrarianTimeSlot)" @click="deleteLibrarianTimeSlot(deleteLibrarianTimeSlot)">Delete Librarian Time Slot</button>
            </tr>
        </table>
        <br>
        <table align="center">
            <tr>
                <th><label>Time Slot ID:</label></th>
                <td><input type="text" v-model="librarianTimeSlotId" placeholder="LibrarianTimeSlotID"></td>
            </tr>
            <tr>
                <th><label>Librarian ID:</label></th>
                <td><input type="text" v-model="librarian.libraryCardID" placeholder="Librarian ID"></td>
            </tr>
            <tr>
                <th><label>Day of the Week:</label></th>
                <td><input type="text" v-model="day" placeholder="DayOfWeek aDayofTheWeek"></td>
            </tr>
            <tr>
                <th><label>Start Time:</label></th>
                <td><input type="text" v-model="startTime" placeholder="hh:mm:ss"></td>
            </tr>
            <tr>
                <th><label>End Time:</label></th>
                <td><input type="text" v-model="endTime" placeholder="hh:mm:ss"></td>
            </tr>
        </table>
        <br>
        <table align="center">
            <tr>
                <th> Librarian Time Slot ID</th>
                <th> Librarian </th>
                <th> Day </th>
                <th> Start Time </th>
                <th> End Time </th>
                
            <tr v-for= "librariantimeslot in librariantimeslots" :key="librariantimeslot.Librarian">
                <td class="tdstyle">{{ librariantimeslot.librarianTimeSlotId }}</td>
                <td class="tdstyle">{{ librariantimeslot.librarian.libraryCardID }}</td>
                <td class="tdstyle">{{ librariantimeslot.aDayOfTheWeek }}</td>
                <td class="tdstyle">{{ librariantimeslot.startTime }}</td>
                <td class="tdstyle">{{ librariantimeslot.endTime }}</td>
            </tr>
        </table>
     
        <span v-if="errorLibrarianTimeSlot" style="color:red">{{errorLibrarianTimeSlot}}</span>
    </div>
</template>

<script src="./HeadLibrarianTimeSlot.js">

</script>

<style>
.button{
    padding: 10px 20px;
    font-size: 12px;
    border-radius: 10px;
    background-color: #42b983;
}
.tables {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 70%;
}

.td, .thstyle {
  border: 1px solid #dddddd;
  text-align: center;
  padding: 8px;
}

.th {
  border: 1px solid #dddddd;
  text-align: center;
  padding: 8px;
}
</style>