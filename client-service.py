"""
    This is a sample client service that uses our API server
    API URLS
        !api/v1/client/register
            for POST
        !api/v1/comment/{{commentId}}
            for GET PUT DELETE
        !api/v1/post/{{postId}}/comment
            for POST
        !api/v1/user
            for POST
        !api/v1/user/{{userId}}
            for GET PUT DELETE
        !api/v1/post
            for POST
        !api/v1/post/{{postId}}
            for GET PUT DELETE

"""

import json
import requests

url = "http://localhost:8080/"
headers = {"Content-Type": "application/json"}


#register client
def register_client():
    
    body = {'clientName': clientName}
    response = requests.post(url+"api/v1/client/register", headers=headers, data=json.dumps(body))
    return response.json()
#create user in database
def create_user():
    
    body = {
        "firstName": user1[0],
        "lastName": user1[1],
        "clientId": clientID
    }
    response = requests.post(url+"api/v1/user", headers=headers, data=json.dumps(body))
    return response.json()
#get user by id
def get_user(user1ID):
    body = {
        "firstName": user1[0],
        "lastName": user1[1],
        "clientId": clientID
    }
    response = requests.get(url+"api/v1/user/"+user1ID, headers=headers, data=json.dumps(body))
    return response.json()

#update user by id
def update_user(user1ID):
    body = {
        "firstName": user_update[0],
        "lastName": user_update[1],
        "clientId": clientID
    }
    response = requests.put(url+"api/v1/user/"+user1ID, headers=headers, data=json.dumps(body))
    return response.json()

#delete user by id
def delete_user(user1ID):
    body = {
        "clientId": clientID
    }
    response = requests.delete(url+"api/v1/user/"+user1ID, headers=headers, data=json.dumps(body))
    return response.json()

#create a post for user1
def create_post_for_user():
    body = {
        "userId": user1ID,
        "clientId": clientID,
        'title': post1[0],
        'content': post1[1],
        'tags': post1[2]
    }
    response = requests.post(url+"api/v1/post", headers=headers, data=json.dumps(body))
    return response.json()

#get a post by id
def getpost_byId(post1ID):
    
    body = {
        "clientId": clientID
        
    }
    response = requests.get(url+"api/v1/post/"+post1ID, headers=headers, data=json.dumps(body))
    return response.json()

#update a post by id
def updatepost_byId(post1ID):
    
    body = {
        "userId": user1ID,
        "clientId": clientID,
        'title': post_update[0],
        'content': post_update[1],
        'tags': post_update[2]
    }
    response = requests.put(url+"api/v1/post/"+post1ID, headers=headers, data=json.dumps(body))
    return response.json()

#delete a post by id
def deletepost_byId(post1ID):
    
    body = {
        "clientId": clientID
    }
    response = requests.delete(url+"api/v1/post/"+post1ID, headers=headers, data=json.dumps(body))
    return response.json()

#create a comment for post1
def create_comment_for_post(post1ID):
    
    body = {
        "userId": user1ID,
        "clientId": clientID,
        'content': comment1[0],
        'LikesNum': comment1[1],
        'dislikesNum': comment1[2]
    }
    response = requests.post(url+"api/v1/post/"+post1ID+"/comment", headers=headers, data=json.dumps(body))
    return response.json()

#get a comment by id
def getcomment_byId(comment1ID):
    
    body = {
        "clientId": clientID  
    }
    response = requests.get(url+"api/v1/comment/"+comment1ID, headers=headers, data=json.dumps(body))
    return response.json()

#update a comment by id
def updatecomment_byId(comment1ID):
    
    body = {
        "userId": user1ID,
        "clientId": clientID,
        'content': comment_update[0],
        'LikesNum': comment_update[1],
        'dislikesNum': comment_update[2]
    }
    response = requests.put(url+"api/v1/comment/"+comment1ID, headers=headers, data=json.dumps(body))
    return response.json()

#delete a comment by id
def deletecomment_byId(comment1ID):
    
    body = {
        "clientId": clientID
    }
    response = requests.delete(url+"api/v1/comment/"+comment1ID, headers=headers, data=json.dumps(body))
    return response.json()


if __name__ == '__main__':
    
    clientName = 'POST-A-POST'
    #register this client in the server
    clientID = register_client()["clientId"]
    print('client_id,',clientID)

    #sample data from frontend
    #sample user 
    user1 = ['Insomia','Team'] #first and last name
    user_update = ['INSOMNIA','TEAM'] #updated info on user1ID
    #sample post
    post1 = ["COMS4156-in progress","Final Project-in progress",list({'second-iteration-in progress'})] #post title, content, tags
    post_update = ["COMS4156-complete","Final Project-complete",list({'second-iteration-complete'})] # updated info on post title, content, tags
    #sample comment
    comment1 = ['well done',5,10]
    comment_update = ['very nice',15,20]

    #create this user in the server
    user1ID = create_user()['userId']
    print('user_id,',user1ID)
    #print(get_user(user1ID))
    #print(update_user(user1ID))
    
    #create a post for user1
    post1ID = create_post_for_user()['postId']
    print("post_id,",post1ID)
    #print(getpost_byId(post1ID))
    #print(updatepost_byId(post1ID))

    #create a comment on post1
    comment1ID = create_comment_for_post(post1ID)['commentId']
    print('comment_id,',comment1ID)
    #print(getcomment_byId(comment1ID))
    #print(updatecomment_byId(comment1ID))
    
    #delete entity in this order
    #print(deletecomment_byId(comment1ID))
    #print(deletepost_byId(post1ID))
    #print(delete_user(user1ID))
    