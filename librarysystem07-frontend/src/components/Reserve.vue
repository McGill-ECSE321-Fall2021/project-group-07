<template>
    <div id="reserve">

                <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
                <h3 class="navbar-brand">McLennan Library Online</h3>
                <!--div class="collapse navbar-collapse" id="navbarNavAltMarkup"-->
                    <div class="nav navbar-nav" routerLinkActive="active">
                        <router-link class="nav-item nav-link" to="/info"> My Account </router-link>
                        <router-link class="nav-item nav-link" to="/browse"> Reserve a Book </router-link> 
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
                <th>Code</th>
            </tr>
            <tr v-for="inventoryItem in inventoryItems" :key="inventoryItem.item">
                <td>{{inventoryItem.item.name}}</td>
                <td>{{inventoryItem.item.author}}</td>
                <td>{{inventoryItem.item.type}}</td>
                <td>{{inventoryItem.item.duplicates}}</td>
                <td>inventoryItem.item.status</td>
                <td>inventoryItem.item.inventoryItemId</td>
            </tr>
        </table>

        <select v-model="selectedItem">
            ​   <option disabled value="">Please select one</option>
       ​        <option v-for="inventoryItem in inventoryItems" :key="inventoryItem.item">
                     ​  <td>{{ inventoryItem.item.name }}</td>
             ​  </option>
        </select>
        <button @click="reserve(selectedItem)">Reserve</button>
        <span v-if="errorVisitor" style="color:red">Error: {{ errorVisitorAddress }}</span>

    </div>
</template>


<script src="./browse.js">
</script>


<style>
  * {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f0efea;
  }
    table {
        border-collapse: collapse;
        border-spacing: 0;
        width: 100%;
        padding-left: 60px;
        padding-right: 60px;
        align-content: center ;
    }
    td {
        border-left: 1px solid #2c3e50;
        text-align: center;
        margin-top: 5px;
    }
    select {
        margin-top: 30px;
        align-content: center;
    }
    button {
        margin-top: 30px;
        margin-left: 30px;
        align-content: center;
        padding: -10px -24px -10px -25px;
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