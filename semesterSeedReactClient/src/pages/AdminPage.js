import React, { Component } from 'react'
import {observer} from "mobx-react";
import userData from "../stores/adminStore";
const URL = require("../../package.json").serverURL;
import fetchHelper from "../stores/fetchHelpers"
import {observable, action, computed} from "mobx";


@observer
  class AdminPage extends Component {

    constructor (props){
        super(props);
        this.delete = this.delete.bind(this);

    }
    @action
    setErrorMessage(err) {
        this.errorMessage = err;
    }
    @action
    delete(event) {
        this.errorMessage = "";
        this.messageFromServer = "";
        let errorCode = 200;
        let username = event.target.id;
        console.log("rwrttr" + username);
        const options = fetchHelper.makeOptions("DELETE", true);
        fetch(URL + "api/demouser/"+username, options)
            .then((res) => {
                return res.json();
            })
            .then((res) => {
                this.setData(res);
                console.log(res);
            }).catch(err => {
            //This is the only way (I have found) to verify server is not running
            this.setErrorMessage(fetchHelper.addJustErrorMessage(err));
        })
    }

    componentWillMount() {
      /*
      This will fetch data each time you navigate to this route
      Move to constructor, if only required once, or add "logic" to determine when data should be "refetched"
      */
       // var users = userData.getData();
      userData.getData();
    }

    render() {

        var users = userData.users;
        var lines = users.map((user, index)=> <tr key={index}>



            <td>{user.username}</td>

            <td>
                <button onClick={this.delete} id={user.username}>delete</button>
            </td>

        </tr>);
      return (
        <div className="App">

                <table className="table table-striped">
                    <thead>
                    <tr>
                        <th>List of user</th>

                    </tr>
                    </thead>
                    <tbody id="userTable">
                    {lines}
                    </tbody>
                </table>


                {/*<ul>*/}
                {/*{userData.users.map((user, index) =>*/}
                    {/*<li key={index}>{user.username}</li>)}*/}
            {/*</ul>*/}
        </div>
      )
    }

  }

export default AdminPage;