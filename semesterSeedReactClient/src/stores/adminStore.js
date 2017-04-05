import {observable, action, computed} from "mobx";
import fetchHelper from "./fetchHelpers"
const URL = require("../../package.json").serverURL;

/* encapsulates Data related to Admins */
class AdminStore {
    @observable _users = [];
    @observable messageFromServer = "";
    @observable errorMessage = "";

    @action
    setErrorMessage(err) {
        this.errorMessage = err;
    }

    @action
    setMessageFromServer(msg) {
        this.messageFromServer = msg;
    }

    @action
    setData(res) {
        this._users.replace(res)
    }

    @computed
    get users() {
        return this._users;
    }

    @action
    getData = () => {
        this.errorMessage = "";
        this.messageFromServer = "";
        const options = fetchHelper.makeOptions("GET", true);
        fetch(URL + "api/demouser/complete", options)
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
    @action
    setData = () => {
        this.errorMessage = "";
        this.messageFromServer = "";
        const options = fetchHelper.makeOptions("POST", true);
        fetch(URL + "api/demouser/complete", options)
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
}
let adminStore = new AdminStore(URL);

//Only for debugging
//window.adminStore = adminStore;
export default adminStore;
