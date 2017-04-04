import {observable, computed, action, useStrict} from "mobx";
useStrict(true)
//DataStore for this Demo
class BookStore {
    @observable
    _books = [];


    constructor() {
        this._books.replace([
            {
                title: "Test title 1",
                info: "Test info 1"
            },
            {
                title: "Test title 2",
                info: "Test info 2",
                moreInfo: "Test moreInfo 1"
            }
        ])
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
        this._books.push(book);
    }
}
export default new BookStore();