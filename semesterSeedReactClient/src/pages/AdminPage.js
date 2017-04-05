import React, { Component } from 'react'
import {observer} from "mobx-react";
import userData from "../stores/adminStore";

@observer
  class AdminPage extends Component {

    componentWillMount() {
      /*
      This will fetch data each time you navigate to this route
      Move to constructor, if only required once, or add "logic" to determine when data should be "refetched"
      */
      userData.getData();
    }

    render() {
      return (
        <div>
            <h2>List of Users</h2>
            <ul>
                {userData.users.map((user, index) =>
                    <li key={index}>{user.username}</li>)}
            </ul>
        </div>
      )
    }

  }

export default AdminPage;