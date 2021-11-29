<template>

    <div id="account">

        <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand">McLennon Library Online</a>
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div class="nav navbar-nav" routerLinkActive="active">
                        <a class="nav-item nav-link active" router-link to="/account">My Account<span class="sr-only">(current)</span></a>
                        <a class="nav-item nav-link" router-link to="/reserve">Reserve A Book</a>
                        <a class="nav-item nav-link" router-link to="/reservations">View My Reservations</a>
                        <a class="nav-item nav-link" router-link to="/event">Submit Event</a>
                    </div>
                </div>
        </nav>

        <router-outlet></router-outlet>
    
        <body>

            <div id="displayVisitorDetails">

            <h2>Account Summary</h2>
            <p id="visitorName"></p>
            <p id="visitorUsername"></p>
            <p id="visitorAddress"></p>
            <p id="visitorLibraryCardId"></p>

            </div>

            <p>change address</p>
            <input type="text" v-model="visitorLoggedIn.address" placeholder="New Address">
            <button @click="updateVisitorAddress(visitorLoggedIn.address)">Change Address</button>
            <span v-if="errorVisitor" style="color:red">Error: {{ errorVisitorAddress }}</span>

        </body>

    </div>

</template>


<script src="./accountInfo.js">
</script>


<style>
    * {
        box-sizing: border-box;
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        color: #2c3e50;
        background: #f1eaef;
    }
    button {
        margin-top: 10px;
        margin-bottom: 10px;
        padding: -10px -24px -10px -25px;
        border: 2px solid #2c3e50;
        border-radius: 4px;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
    }
    button:hover {
        background-color: #d6c2d0;
        color: white;
    }
    a {
        display: block;
        padding: 8px;
        background-color: #dddddd;
    }
</style>