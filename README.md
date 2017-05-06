# mavlink_mockup

User Management System completed. Additional features include:

- Robust security / permission system set up via Spring Security framework and role-based SQL tables

- Cookie generated for remembering the user

- CSS borrowed from UNO's MavLink website, along with some personal modifications and Bootstrap framework

- Forget password feature asks user to provide their username and password in order to change password. This creates a USER object, compares it to the existing USER data from the SQL database, and if it's the same it will update the USER record.

- Passwords are stored with SHA-1 and a SALT.

- Test cases that verify login, logout, user and admin permissions, user profile modification, course listing, user listing, etc. This is done via JUnit and Selenium (JARs already implemented)

- Additional information such as courses can be viewed. The original goal was to have users register, and then select classes they wanted for the next semester. I didn't have enough time due to other projects, finals, etc. However I think you can see the intention.

- Users must provide a unique username and e-mail otherwise registration will not be completed.

- Some Javascript implemented so users can see their passwords via a checkbox. Also requires users to have both a username and password typed to log in (AKA putting the load on the client). 
