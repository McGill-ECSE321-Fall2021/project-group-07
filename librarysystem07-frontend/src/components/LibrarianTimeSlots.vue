<template>
    <div id="headLibrarianTimeSlot">
        <h2>Librarian Time Slot</h2>
		<h4>Exisiting Librarian Time Slots:</h4>
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
        <br>
        <span v-if="errorLibrarianTimeSlot" style="color:red">{{errorLibrarianTimeSlot}}</span>
    </div>
</template>

<script src="./HeadLibrarianTimeSlot.js">

</script>

<style>
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