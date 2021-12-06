<template>
    <div id="reserve">
          <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
                <h3 class="navbar-brand">McLennan Library Online</h3>
                    <div class="nav navbar-nav" routerLinkActive="active">
                        <router-link class="nav-item nav-link" to="/info"> My Account </router-link>
                        <router-link class="nav-item nav-link" to="/browse-librarian"> Inventory </router-link> 
                        <router-link class="nav-item nav-link" to="/headlibrariantimeslots"> Schedule </router-link> 
                        <router-link class="nav-item nav-link" to="/event"> Request Event </router-link> 
                    </div>
        </nav>
        <h2>Browse Items</h2>

        <table>
            <tr>
                <th>Name</th>
                <th>Author</th>
                <th>Type</th>
                <th>Stock</th>
                <th>Availability Status</th>
            </tr>
            <tr v-for="inventoryItem in inventoryItems" :key="inventoryItem.item">
                <td>{{inventoryItem.item.name}}</td>
                <td>{{inventoryItem.item.author}}</td>
                <td>{{inventoryItem.item.type}}</td>
                <td>{{inventoryItem.item.duplicates}}</td>
                <td>{{inventoryItem.item.status}}</td>
            </tr>
        </table>

<h2>Reserve an item</h2>
        <select v-model="selectedItem">
            ​   <option disabled value="">Please select one</option>
       ​        <option v-for="inventoryItem in inventoryItems" :key="inventoryItem.item">
                     ​  <td>{{ inventoryItem.item.name }}  -  {{inventoryItem.item.author}}</td>
             ​  </option>
        </select>
       
        <button @click="reserveItem(selectedItem)">Reserve</button>
        <h2>Add an item to inventory</h2>
        <input type="text" v-model="duplicates" placeholder="Duplicates">
        <input type="text" v-model="name" placeholder="Name">
        <input type="text" v-model="author" placeholder="Author">
        <input type="text" v-model="status" placeholder="Status">
        <input type="text" v-model="type" placeholder="Type">

        <button @click="createItem(duplicates, name, author, status, type)">Create</button>
        <span v-if="errorVisitor" style="color:red">Error: {{ errorVisitorAddress }}</span>
        <!--audio controls autoplay>
            <source src="https://drive.google.com/file/d/1LErgvn2U9hoc5HsLukDkuHh9Ip_T2d_n/view?usp=sharing" preload="auto">
  Your browser does not support the audio tag.
        </audio-->
    </div>
</template>


<script src="./browselibrarian.js">
</script>


<style>
    * {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        color: #2c3e50;
        background: #f0efea;
    }
    h2 {
        padding-top: 30px;
    }
    table {
        border-collapse: collapse;
        border-spacing: 0;
        width: 100%;
        padding-left: 60px;
        padding-right: 60px;
        align-content: center ;
    }
    th {
        margin-bottom: 15px;
    }
    td {
        border-left: 1px solid #2c3e50;
        text-align: center;
        margin-top: 5px;
    }
 
    h3 {
        padding-top: 30px;
        margin-top: 10px;
    }
    nav, h3, router-link, #nav navbar-nav {
        background-color: whitesmoke;
    }
    button {
        margin-left: 30px;
        align-content: center;
        border: 1px solid #2c3e50;
        border-radius: 2px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
    }
    button:hover{
        background-color: #cfccc7;
    }
</style>