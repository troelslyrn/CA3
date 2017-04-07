import {observable, computed, action, useStrict} from "mobx";
import fetchHelper from "./fetchHelpers"
const URL = require("../../package.json").serverURL;

useStrict(true)

class BookStore {

    @observable _books = [];

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
        this._books.replace(res);
    }

    @computed
    get bookCount() {
        return this._books.length;
    }

    @computed
    get books() {
        return this._books;
    }

    @action
    addBook(book) {
        const options = fetchHelper.makeOptions("POST", true, book);
        fetch(URL + "api/book", options)
            .then((res) => {
                return res.json();
            })
    }

    @action
    deleteBook(id) {
        const options = fetchHelper.makeOptions("DELETE", true, id);
        fetch(URL + "api/book/" + id, options)
            .then((res) => {
            return res.json();
            })
    }

    @action
    getData = () => {
        this.errorMessage = "";
        this.messageFromServer = "";
        let errorCode = 200;
        const options = fetchHelper.makeOptions("GET", true);
        fetch(URL + "api/book/complete", options)
            .then((res) => {
                //if (res.status > 200 || !res.ok) {
                //   errorCode = res.status;
                //}
                return res.json();
            })
            .then((res) => {
                //if (errorCode !== 200) {
                //  throw new Error(`${res.error.message} (${res.error.code})`);
                //}
                //else {
                this.setData(res);
                //}
            }).catch(err => {
            //This is the only way (I have found) to verify server is not running
            this.setErrorMessage(fetchHelper.addJustErrorMessage(err));
        })
    }

}
let bookStore = new BookStore(URL);
export default bookStore;