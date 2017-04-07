import React from "react";
import {Link} from "react-router"
import bookStore from "../stores/bookStore";

export default class Details extends React.Component {

    updateBook(book) {
        bookStore.updateBook(book);
    }

    deleteBook(id) {
        bookStore.deleteBook(id);
    }

    render() {
        let id = this.props.params.id;
        let book = this.props.route.books.filter((book, index) => {
            return index === Number(id);
        })[0];
        return (
            <div>
                <p><input id="title" type="text" placeholder="title" defaultValue={book.title}/></p>
                <p><input id="info" type="text" placeholder="info" defaultValue={book.info}/></p>
                <p><input id="moreInfo" type="text" placeholder="moreInfo" defaultValue={book.moreInfo}/></p>

                <Link to="products">
                    <button onClick={this.updateBook.bind(this, book)}>Update</button>
                    <button onClick={this.deleteBook.bind(this, book.id)}>Delete</button>
                </Link>
                <br />
                <Link to="/products">Products</Link>
            </div>
        );
    }
}