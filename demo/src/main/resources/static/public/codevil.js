// let token = "";


function login() {
    let _text = document.getElementById("text").value;
    console.log(_text);
    let _text2 = document.getElementById("text2").value;
    console.log(_text2);

    fetch('http://localhost:8080/user/login', {
        method: 'post',
        body: JSON.stringify({
            username: _text,
            password: _text2
        }),
        headers: new Headers({'content-type': 'application/json',
                            'authorization': 'Bearer ',
                        }),
        }, console.log("login")
    )
    .then( async (response) => {

        // get json response here
        let data = await response.json();
        console.log(response.status)
        if(response.status === 200){
            console.log(data);
            // token = data['token'];
            sessionStorage.setItem('loginToken', data['token']);
            location.href = 'startPageCodevil.html';
            // console.log(token);
            sessionStorage.setItem('username', _text);
        }
    })
}





function register() {
    let _text = document.getElementById("text").value;
    console.log(_text);
    let _text2 = document.getElementById("text2").value;
    console.log(_text2);


    let _type = document.getElementById("type").value;

    fetch('http://localhost:8080/user/register', {
        method: 'post',
        body: JSON.stringify({
            username: _text,
            password: _text2,
            type: _type
        }),
        headers: new Headers({'content-type': 'application/json',
                            'authorization': 'Bearer ',
                        }),
        }, console.log("register")
    )
    .then( async (response) => {

// get json response here
let data = await response.json();
console.log(response.status)
if(response.status === 200){
    console.log(data);
    location.href = 'loginCodevil.html';
}


})
}













function createApp() {
    let _text = document.getElementById("title").value;
    console.log(_text);
    let _text2 = document.getElementById("text").value;
    console.log(_text2);
    

    fetch('http://localhost:8080/apps/createApp', {
        method: 'post',
        body: JSON.stringify({
            title: _text,
            text: _text2
        }),
        headers: new Headers({'content-type': 'application/json',
                            'authorization': 'Bearer ' + sessionStorage.getItem('loginToken')
                        }),
        }, 
    )
    .then( async (response) => {

// get json response here
let data = await response.json();
console.log(response.status)
if(response.status === 200){
    console.log(data);
    location.href = 'startPageCodevil.html';
}


})
}





function getAllApps(){
    fetch('http://localhost:8080/apps/getAllApps', {
        method: 'get',
        headers: new Headers({'content-type': 'application/json',
                            'Authorization': 'Bearer ' + sessionStorage.getItem('loginToken'),
    

                        }),
        }, 
    )
    .then( async (response) => {

// get json response here
let data = await response.json();
console.log(response.status);
console.log(data);
if(response.status === 200){
//    console.log(data);

    let jobApplicationElement = document.getElementById("job_applications");
    console.log(data);
    for(let i = 0; i < data.length; i++){
        console.log(data[i]["id"]);
        let div = document.createElement("div");
        div.setAttribute("id", "application" + data[i]["id"]);
        const employer = document.createElement("p");
        const line = document.createElement("br");
        const node = document.createElement("p");
        const node2 = document.createElement("p");
        const button1 = document.createElement("button");
        const comment = document.createElement("button");
        const AddComment = document.createElement("input");
        const addCommentBtn = document.createElement("button");
        button1.setAttribute("id", data[i]["id"]);
            button1.setAttribute("onclick", "volunteer(this.id)");
        div.setAttribute("class", "app");

//        <a href="commentsCodevil.html">comments</a>
//        comment.setAttribute("id", "comments");
        let currentId = data[i]["id"];
//        comment.setAttribute("onclick", "showComments(this.currentId)");
        comment.setAttribute("onclick", "showComments(" + currentId + ")");
        AddComment.setAttribute("type", "text");
        AddComment.setAttribute("id", "text" + currentId);
        addCommentBtn.setAttribute("onclick", "addComment(" + currentId + "), location.href = 'startPageCodevil.html'");
        button1.setAttribute("class", "blank");
        comment.setAttribute("class", "blank");
        AddComment.setAttribute("class", "blank");
        addCommentBtn.setAttribute("class", "blank");
        employer.setAttribute("class", "title1");
        node.setAttribute("class", "title1");
        node2.setAttribute("class", "text1");

        div.appendChild(employer);
        div.appendChild(node);
        div.appendChild(line);
        div.appendChild(node2);
        div.appendChild(line);
        div.appendChild(button1);
        div.appendChild(line);
        div.appendChild(comment);
        div.appendChild(line);
        div.appendChild(AddComment);
        div.appendChild(line);
        div.appendChild(addCommentBtn);
        div.appendChild(line);
        jobApplicationElement.appendChild(div);


//        if(data[i]["volunteer_id"] === null){
//                    button1.appendChild(document.createTextNode("Apply for this Job App"));
//                    button1.setAttribute("onclick", "volunteer(this.id)");
//                }
//                else{
//                    button1.appendChild(document.createTextNode("There is already volunteer for this job"));
//                }

        employer.appendChild(document.createTextNode(data[i]["employer"]["username"] + "'s Job Application"));
        button1.appendChild(document.createTextNode("Apply for this Job App"));
        node.appendChild(document.createTextNode(data[i]["title"]));
        node2.appendChild(document.createTextNode(data[i]["text"]));
        comment.appendChild(document.createTextNode("comments"));
        AddComment.appendChild(document.createTextNode("comments"));
        addCommentBtn.appendChild(document.createTextNode("Add Comment"));

        jobApplicationElement.append(div);

    }
}


})
}


function volunteer(_id){

        fetch('http://localhost:8080/apps/updateApp', {
            method: 'post',
            body: JSON.stringify({
                id: _id
            }),
            headers: new Headers({'content-type': 'application/json',
                                'authorization': 'Bearer ' + sessionStorage.getItem('loginToken')
                            }),
            },
        )
        .then( async (response) => {

    // get json response here
    let data = await response.json();
    console.log(response.status)
    if(response.status === 200){
        console.log(data);
    }


    })
}




function showComments(_jobId){
    console.log(_jobId);
    fetch('http://localhost:8080/comments/showComments', {
        method: 'post',
        body: JSON.stringify({
            id: _jobId
        }),
        headers: new Headers({'content-type': 'application/json',
                            'Authorization': 'Bearer ' + sessionStorage.getItem('loginToken'),


                        }),
        },
    )
    .then( async (response) => {

// get json response here
let data = await response.json();
console.log(response.status)
if(response.status === 200){
//    console.log(data);


    console.log(data.length);
    for(let i = 0; i < data.length; i++){
        console.log(data);
        let commentElement = document.getElementById("application" + _jobId);
        const username = document.createElement("p");
        const comment = document.createElement("p");
        username.setAttribute("class", "usernameC");

        comment.appendChild(document.createTextNode(data[i]["userId"]["username"] + ": "));

        comment.appendChild(document.createTextNode(data[i]["text"]));
        if(data[i]["userId"]["username"] === sessionStorage.getItem('username')){
            comment.setAttribute("class", "myComment");
        }
        else{
            comment.setAttribute("class", "comment");
        }

        console.log(data[i]["text"]);
        commentElement.append(comment);
    }
}


})
}


function addComment(_jobAppId){
    let _text = document.getElementById("text" + _jobAppId).value;
    fetch('http://localhost:8080/comments/addComment', {
            method: 'post',
            body: JSON.stringify({
                text: _text,
                joApplicationId: _jobAppId
            }),
            headers: new Headers({'content-type': 'application/json',
                                'Authorization': 'Bearer ' + sessionStorage.getItem('loginToken'),


                            }),
            },
        )
        .then( async (response) => {

    // get json response here
    let data = await response.json();
    console.log(response.status)
    if(response.status === 200){
        console.log(data);
    }


    })

}


function logout(){
    sessionStorage.setItem('loginToken', null);
    location.href = 'mainPageCodevil.html';
}





function getAppliedApps(){
    fetch('http://localhost:8080/apps/getAppliedApps', {
        method: 'get',
        headers: new Headers({'content-type': 'application/json',
                            'Authorization': 'Bearer ' + sessionStorage.getItem('loginToken'),


                        }),
        },
    )
    .then( async (response) => {

// get json response here
let data = await response.json();
console.log(response.status);
console.log(data);
if(response.status === 200){
//    console.log(data);

    let jobApplicationElement = document.getElementById("job_applications");
    console.log(data);
    for(let i = 0; i < data.length; i++){
        console.log(data[i]["id"]);
                let div = document.createElement("div");
                div.setAttribute("id", "application" + data[i]["id"]);
                const employer = document.createElement("p");
                const volunteer = document.createElement("p");
                const line = document.createElement("br");
                const node = document.createElement("p");
                const node2 = document.createElement("p");
                const comment = document.createElement("button");
                const AddComment = document.createElement("input");
                const addCommentBtn = document.createElement("button");
                div.setAttribute("class", "app");

        //        <a href="commentsCodevil.html">comments</a>
        //        comment.setAttribute("id", "comments");
                let currentId = data[i]["id"];
        //        comment.setAttribute("onclick", "showComments(this.currentId)");
                comment.setAttribute("onclick", "showComments(" + currentId + ")");
                AddComment.setAttribute("type", "text");
                AddComment.setAttribute("id", "text" + currentId);
                addCommentBtn.setAttribute("onclick", "addComment(" + currentId + ")");
                comment.setAttribute("class", "blank");
                AddComment.setAttribute("class", "blank");
                addCommentBtn.setAttribute("class", "blank");
                employer.setAttribute("class", "title1");
                volunteer.setAttribute("class", "title1")
                node.setAttribute("class", "title1");
                node2.setAttribute("class", "text1");

                div.appendChild(employer);
                div.appendChild(volunteer);
                div.appendChild(node);
                div.appendChild(line);
                div.appendChild(node2);
                div.appendChild(line);
                div.appendChild(comment);
                div.appendChild(line);
                div.appendChild(AddComment);
                div.appendChild(line);
                div.appendChild(addCommentBtn);
                div.appendChild(line);
                jobApplicationElement.appendChild(div);


        //        if(data[i]["volunteer_id"] === null){
        //                    button1.appendChild(document.createTextNode("Apply for this Job App"));
        //                    button1.setAttribute("onclick", "volunteer(this.id)");
        //                }
        //                else{
        //                    button1.appendChild(document.createTextNode("There is already volunteer for this job"));
        //                }

                employer.appendChild(document.createTextNode(data[i]["employer"]["username"] + "'s Job Application"));
                volunteer.appendChild(document.createTextNode("Volunteer is: " + data[i]["volunteer"]["username"]));
                node.appendChild(document.createTextNode(data[i]["title"]));
                node2.appendChild(document.createTextNode(data[i]["text"]));
                comment.appendChild(document.createTextNode("comments"));
                AddComment.appendChild(document.createTextNode("comments"));
                addCommentBtn.appendChild(document.createTextNode("Add Comment"));

                jobApplicationElement.append(div);

//        var newElem = document.createElement("br");
//        console.log(data[i]["id"]);
//        let div = document.createElement("div");
//        div.setAttribute("id", "application" + data[i]["id"]);
//        const creator = document.createElement("p");
//        const br = document.createElement("span");
//        br.innerHTML = "<br/>";
//        const node = document.createElement("p");
//        const node2 = document.createElement("p");
//        const button1 = document.createElement("p");
//        const comment = document.createElement("button");
//        const AddComment = document.createElement("input");
//        const addCommentBtn = document.createElement("button");
//        button1.setAttribute("id", data[i]["id"]);
//
////        <a href="commentsCodevil.html">comments</a>
////        comment.setAttribute("id", "comments");
//        let currentId = data[i]["id"];
////        comment.setAttribute("onclick", "showComments(this.currentId)");
//        comment.setAttribute("onclick", "showComments(" + currentId + ")");
//        AddComment.setAttribute("type", "text");
//        AddComment.setAttribute("id", "text" + currentId);
//        addCommentBtn.setAttribute("onclick", "addComment(" + currentId + ")");
//        button1.setAttribute("class", "blank");
//        comment.setAttribute("class", "blank");
//        AddComment.setAttribute("class", "blank");
//        addCommentBtn.setAttribute("class", "blank");
//        creator.setAttribute("class", "creator");
//        div.appendChild(creator)
//        div.appendChild(node);
//        div.appendChild(br);
//        div.appendChild(node2);
//        div.appendChild(br);
//        div.appendChild(button1);
//        div.appendChild(br);
//        div.appendChild(comment);
//        div.appendChild(br);
//        div.appendChild(AddComment);
//        div.appendChild(br);
//        div.appendChild(addCommentBtn);
//        div.appendChild(br);
//
//
//
//        jobApplicationElement.appendChild(div);
//
//
////        if(data[i]["volunteer_id"] === null){
////                    button1.appendChild(document.createTextNode("Apply for this Job App"));
////                    button1.setAttribute("onclick", "volunteer(this.id)");
////                }
////                else{
////                    button1.appendChild(document.createTextNode("There is already volunteer for this job"));
////                }
//
//        creator.appendChild(document.createTextNode(data[i]["employer"]["username"] + "'s job application"));
//        button1.appendChild(document.createTextNode("Volunteer is: " + data[i]["volunteer"]["username"]));
//        node.appendChild(document.createTextNode(data[i]["title"]));
//        node2.appendChild(document.createTextNode(data[i]["text"]));
//        comment.appendChild(document.createTextNode("comments"));
//        AddComment.appendChild(document.createTextNode("comments"));
//        addCommentBtn.appendChild(document.createTextNode("Add Comment"));
//
//        jobApplicationElement.append(div);



    }
}


})
}






