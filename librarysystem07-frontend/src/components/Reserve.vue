<template>

    <div id="reserve">

        <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand">McLennon Library Online</a>
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div class="nav navbar-nav" routerLinkActive="active">
                        <a class="nav-item nav-link" router-link to="/account">My Account</a>
                        <a class="nav-item nav-link active" router-link to="/reserve">Reserve A Book<span class="sr-only">(current)</span></a>
                        <a class="nav-item nav-link" router-link to="/reservations">View My Reservations</a>
                        <a class="nav-item nav-link" router-link to="/event">Submit Event</a>
                    </div>
                </div>
        </nav>

        <router-outlet></router-outlet>
    
        <body>

            <div id="displayVisitorDetails">

            <h2>Browse Items In The McLennon Library</h2>

            <tr>
                <th>Name</th>
                <th>Author</th>
                <th>Type</th>
                <th>Stock</th>
                <th>Availability Status</th>
                <th>Code</th>
            </tr>

            ​<select v-model="selectedItem">
            ​   <option disabled value="">Please select one</option>
       ​        <option v-for="inventoryItem in inventoryItems" v-bind:key="inventoryItem.inventoryItemId">
                     <tr>
                     ​  <th>{{ inventoryItem.name }}</th>
                       <th>{{ inventoryItem.author }}</th>
                       <th>{{ inventoryItem.type }}</th>
                       <th>{{ inventoryItem.duplicates }}</th>
                       <th>{{ inventoryItem.status }}</th>
                       <th>{{ inventoryItem.inventoryItemId }}</th>
                     </tr>
             ​  </option>
            </select>

            </div>

            <button @click="reserve(selectedItem)">Reserve</button>
            <span v-if="errorVisitor" style="color:red">Error: {{ errorVisitorAddress }}</span>

        </body>

    </div>

</template>


<script src="./accountInfo.js">
</script>


<style>
</style>