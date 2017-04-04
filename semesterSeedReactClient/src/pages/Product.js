import React, {Component} from "react";
import { Link, hashHistory} from "react-router";
class Product extends Component {

    render() {
        return (
            <div>
                <h2>Our Products</h2>
                <button className="btn btn-default btn-sm" onClick={this.onNewBook}>Add Book</button>
                <h4>All our great books </h4>
                <ul>
                    {this.props.route.books.map((book, index) => <li key={index}>
                        {book.title} <Link to={`products/details/${index}`}>(details)</Link></li>)}
                </ul>
            </div>
        )
    }

    onNewBook = () =>{
        hashHistory.push('products/newBook');
    }
}
export default Product;