# Android Tap to Pay Sample 

Original code by @tedsch adapted to work with a Stripe account that supports payments in GBP

Sample code that will generate an app that will turn your Android phone into a in-person payment machine.

Modify the variables in vars.kt to get it to work with a different currency / server.  


* Example vars.kt:

        const val LocationID = "tml_E04ONgsItef2Sz";
        const val ServerURL = "https://terminal-backend-acav.herokuapp.com/";
        const val Currency = "gbp";
        const val CountryCode = "GB";
 

   LocationID - resource identifier of [location group](https://stripe.com/docs/terminal/fleet/locations) you want your phone to be registered to

   ServerURL - address of the server that hosts the Stripe back-end service. You can find the code for the back-end used with this example in [https://github.com/aandresdelvalle-stripe/example-terminal-backend](https://github.com/aandresdelvalle-stripe/example-terminal-backend)

   Currency - currency the customer will be charged

   CountryCode - country the currency belongs to


This code will work with the Stripe account in "[test mode](https://stripe.com/docs/test-mode)" and in "live mode". Simply make sure that the LocationID  and the API keys used for the back-end server are both coming from either Test or Live.

Contact aandresdelvalle@stripe.com for further information
