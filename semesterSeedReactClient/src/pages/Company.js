import React, { Component } from 'react'
import { observer } from "mobx-react";

const Company = observer(class Company extends Component {

    render() {
        return (
           <div>
               <h2>Company</h2>
           </div>
        )
    }
})

export default Company;

