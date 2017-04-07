import React from "react"
import bookStore from "../stores/bookStore";
import userData from "../stores/adminStore";
import {observable, action, computed} from "mobx";
import fetchHelper from "../stores/fetchHelpers";
const URL = require("../../package.json").serverURL;

export default class NewUser extends React.Component{
    constructor(){
        super();
        this.state = {user:{userName : "",passwordHash: ""}};
    }

    saveUser = () =>{
        userData.addUser(this.state.user);
        //Clear input fields, to allow for a new "new book"
        this.setState({user:{userName : "",passwordHash: ""}});
        event.preventDefault();//?
    }

    handleChange = (event) => {
        var user = this.state.user;
        var id = event.target.id;
        if(id === "userName"){
            user.userName = event.target.value;
        }
        if(id === "passwordHash"){
            user.passwordHash = event.target.value;
        }

        this.setState({user});
    }

    render(){
        return (
            <div>
                <h2>New user </h2>
                <form onSubmit={this.saveUser}>
                    <input onChange={this.handleChange} value={this.state.user.userName} id="userName" type="text" placeholder="userName"/><br/>
                    <input onChange={this.handleChange} value={this.state.user.passwordHash} id="passwordHash" type="text" placeholder="password"/><br/>
                    <button className="btn btn-sm btn-success">Save User </button>
                </form>
                    <p> {JSON.stringify(this.state.user)}</p>

            </div>
        )
    }

    @action
    setErrorMessage(err) {
        this.errorMessage = err;
    }
}