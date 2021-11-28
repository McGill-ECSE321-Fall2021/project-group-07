<template>
<div id="events">
    <h2>Event sign-up</h2>
    <table>
      <tr>
          <td><b>Create your event!</b></td>
      </tr>
      <tr>
          <td>Name of event</td>
          <td>
              <input type="text" placeholder="Event Name">
          </td>
      </tr>
      <tr>
          <td>Visitor name</td>
          <td>
              <input type="text" placeholder="Visitor Name">
          </td>
          <td>
              <button @click="makeEvent(newEvent.name, newEvent.eventId, newEvent.visitor)">Create</button>
          </td>
      </tr>
    </table>
    <p>
        <span v-if="errorNewEvent" style="color:navy">Error: {{errorNewEvent}} </span>
    </p>
  </div>
</template>



<script src = "./event.js">
</script>



<style>
 #librarysystem07 {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }
</style>