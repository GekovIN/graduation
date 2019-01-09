##**_Restaurant vote system._**

Two type of users: admin and regular user.

Resources:
- Users 
- Dishes (name, price) (catalog), 
- Restaurants (name, address), 
- Menu dishes (date, restaurant, dish),
- Votes (date, restaurant, user).
 
Admin can create restaurant, dish, menu dish (choose from existing dishes).
User can vote for restaurant (only one vote for date, if user vote again - checking vote time, if over - vote denies).

### curl samples (application deployed in application context `graduation`).

#####  Get all restaurants
`curl -s http://localhost:8080/graduation/restaurants --user admin@gmail.com:admin`

#####  Get all restaurants with menus dishes
`curl -s http://localhost:8080/graduation/restaurants/menus --user admin@gmail.com:admin`

##### Get all restaurants with votes
`curl -s http://localhost:8080/graduation/restaurants/votes --user admin@gmail.com:admin`

##### Get restaurants with menus and votes
`curl -s http://localhost:8080/graduation/restaurants/menus-and-votes --user admin@gmail.com:admin`

##### Get restaurant with id 100000
`curl -s http://localhost:8080/graduation/restaurants/100000 --user admin@gmail.com:admin`

##### Get restaurant with id 100000 with menus
`curl -s http://localhost:8080/graduation/restaurants/100000/menus --user admin@gmail.com:admin`

##### Get restaurant with id 100000 with votes
`curl -s http://localhost:8080/graduation/restaurants/100000/votes --user admin@gmail.com:admin`

##### Get restaurant with id 100000 with menus and votes
`curl -s http://localhost:8080/graduation/restaurants/100000/menus-and-votes --user admin@gmail.com:admin`

##### Get restaurants that have menu for date 2018-10-29
`curl -s http://localhost:8080/graduation/restaurants/haveMenu?date=2018-10-29 --user admin@gmail.com:admin`

##### Get restaurants with menu by date 2018-10-31
`curl -s http://localhost:8080/graduation/restaurants/menus?date=2018-10-31 --user admin@gmail.com:admin`

##### Get restaurants with votes by date 2018-10-31
`curl -s http://localhost:8080/graduation/restaurants/votes?date=2018-10-31 --user admin@gmail.com:admin`

##### Get restaurant with id 100000 with menu by date 2018-10-31
`curl -s http://localhost:8080/graduation/restaurants/100000/menus?date=2018-10-31 --user admin@gmail.com:admin`

##### Get restaurant with id 100000 with votes by date 2018-10-31
`curl -s http://localhost:8080/graduation/restaurants/100000/votes?date=2018-10-31 --user admin@gmail.com:admin`

##### Get restaurant with id 100000 with menu and votes by date 2018-10-31
`curl -s http://localhost:8080/graduation/restaurants/100000/menus-and-votes?date=2018-10-31 --user admin@gmail.com:admin`

##### Get restaurantVotesCountTo with id 100000 by date 2018-10-29
`curl -s http://localhost:8080/graduation/restaurants/100000/votes-count?date=2018-10-29 --user admin@gmail.com:admin`

##### Get restaurantsVoteCountTo by date 2018-10-31
`curl -s http://localhost:8080/graduation/restaurants/votes-count?date=2018-10-31 --user admin@gmail.com:admin`

##### Create new restaurant
`curl -s -X POST -d '{"name":"New restaurant", "address":"New address"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/restaurants --user admin@gmail.com:admin`

##### Update restaurant with id 100000
`curl -s -X PUT -d '{"id":"100000", "name":"Updated restaurant", "address":"Updated address"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/restaurants/100000 --user admin@gmail.com:admin`

##### Delete restaurant with id 100000
`curl -s -X DELETE http://localhost:8080/graduation/restaurants/100000 --user admin@gmail.com:admin`
_________________________

#####  Get all votes
`curl -s http://localhost:8080/graduation/votes --user admin@gmail.com:admin`

##### Get vote with id 100005
`curl -s http://localhost:8080/graduation/votes/100005 --user admin@gmail.com:admin`

##### Get profile vote by date 2018-10-31
`curl -s http://localhost:8080/graduation/profile/vote?date=2018-10-31 --user user1@yandex.ru:password1`

##### Get vote by user id and date 2018-10-31
`curl -s http://localhost:8080/graduation/users/100000/vote?date=2018-10-31 --user admin@gmail.com:admin`

##### Profile vote for restaurant
`curl -s -X PUT http://localhost:8080/graduation/profile/vote?restaurantId=100000 --user user1@yandex.ru:password1`

##### Delete vote with id 100000
`curl -s -X DELETE http://localhost:8080/graduation/votes/100000 --user admin@gmail.com:admin`
_________________________

##### Get all menus
`curl -s http://localhost:8080/graduation/menus --user admin@gmail.com:admin`

##### Get menu with id 100000
`curl -s http://localhost:8080/graduation/menus/100000 --user admin@gmail.com:admin`

##### Get menus by date 2018-10-31
`curl -s http://localhost:8080/graduation/menus?date=2018-10-31 --user admin@gmail.com:admin`

##### Delete menu with id 100000
`curl -s -X DELETE http://localhost:8080/graduation/menus/100000 --user admin@gmail.com:admin`

##### Create new menu
`curl -s -X POST -d '{"date":"2018-12-31", "restaurantId":"100000", "dishId":"100000"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/menus --user admin@gmail.com:admin`

##### Update menu with id 100000
`curl -s -X PUT -d '{"id":"100000", "date":"2019-01-01", "restaurantId":"100001", "dishId":"100000"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/menus/100000 --user admin@gmail.com:admin`

_________________________

##### Get all dishes
`curl -s http://localhost:8080/graduation/dishes --user admin@gmail.com:admin`

##### Get dish with id 100000
`curl -s http://localhost:8080/graduation/dishes/100000 --user admin@gmail.com:admin`

##### Delete dish with id 100000
`curl -s -X DELETE http://localhost:8080/graduation/dishes/100000 --user admin@gmail.com:admin`

##### Create new dish
`curl -s -X POST -d '{"name":"New dish",	"price":"1000"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/dishes --user admin@gmail.com:admin`

##### Update dish with id 100000
`curl -s -X PUT -d '{"id":"100000",	"name":"Updated dish", "price":"11111"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/dishes/100000 --user admin@gmail.com:admin`

_________________________

##### Get all users
`curl -s http://localhost:8080/graduation/admin/users --user admin@gmail.com:admin`

##### Get user
`curl -s http://localhost:8080/graduation/admin/users/100000 --user admin@gmail.com:admin`

##### Get user by email
`curl -s http://localhost:8080/graduation/admin/users/by?email=user1@yandex.ru --user admin@gmail.com:admin`

##### Get user by id with votes
`curl -s http://localhost:8080/graduation/admin/users/100000/votes --user admin@gmail.com:admin`

##### Create new user
`curl -s -X POST -d '{"name":"NewUser", "email":"newemail@yandex.ru", "password":"newpassword"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/admin/users --user admin@gmail.com:admin`

##### Update user with id 100000
`curl -s -X PUT -d '{"id":"100000", "name":"UpdatedUser", "email":"updatedemail@yandex.ru", "password":"updatedpassword", "roles": ["ROLE_USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/admin/users/100000 --user admin@gmail.com:admin`

##### Delete user with id 100000
`curl -s -X DELETE http://localhost:8080/graduation/admin/users/100000 --user admin@gmail.com:admin`

_________________________

##### Get profile
`curl -s http://localhost:8080/graduation/profile --user user2@yandex.ru:password2`

##### Get profile with votes
`curl -s http://localhost:8080/graduation/profile/votes --user user2@yandex.ru:password2`

##### Register new profile
`curl -s -X POST -d '{"name":"NewName", "email":"newmail@yandex.ru", "password":"newPassword"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/profile/register`

##### Update profile
`curl -s -X PUT -d '{"id":100001, "name":"Updated name", "email":"updated@gmail.com", "password":"updpassword"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/profile --user user2@yandex.ru:password2`

##### Delete profile
`curl -s -X DELETE http://localhost:8080/graduation/profile --user user3@yandex.ru:password3`
_________________________