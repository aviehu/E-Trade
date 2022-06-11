# E-Trade
The best source for buying and selling online

## Team members:
* Aviehu Yudilevitch - 302344742
* Itay Mor - 205810757
* Hila Klein - 205781917
* Batya Bialik - 315341461
* Sahar Kalifa - 311438709

![alt text](logo.jpg)

state configuration file:

the file is of json type where each element in the array
is an object with 2 fields: function, params

on start up the system runs through the array and for each
object runs the corresponding function on the system

function is a string with the name of the function you wish
the system to run.

params is an array with the parameters for the function above

login and open store example:
````
[
    {
        function: "login",
        params: ["userName", "password"]
    },
    {
        function: "openStore",
        params: ["userName", "storeName", "card"]
    }
]
````