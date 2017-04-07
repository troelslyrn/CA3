import React, {Component} from "react";
import {hashHistory, Link, Route} from "react-router";
import {observer} from "mobx-react"
import NewBook from "./NewBook"

@observer
class Product extends Component {

    componentWillMount() {
        this.props.route.bookStore.getData();
    }


    render() {
        return (
            <div>
                <h2>Our Products</h2>
                <Link to="products/new-book">Add Book</Link>
                <h4>We have {this.props.route.bookStore.bookCount} books in the database</h4>
                <ul>
                    {this.props.route.bookStore.books.map((book, index) => <li key={index}>
                        {book.title} <Link to={`products/details/${index}`}>(details)</Link></li>)}
                </ul>

            </div>
        )
    }

    onNewBook = () => {
        hashHistory.push('products/new-book');
    }
}
export default Product;