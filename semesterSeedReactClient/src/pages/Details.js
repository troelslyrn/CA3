import React from "react";
import {Link} from "react-router"
export default class Details extends React.Component {
    render() {
        let id = this.props.params.id;
        let book = this.props.route.books.filter((book, index) => {
            return index === Number(id);
        })[0];
        return (
            <div>
                <h3 style={{color: "steelblue"}}>Detailed info for the title: {book.Title}</h3>
                <h4> {book.Info}</h4>
                <h4>{book.MoreInfo}</h4>
                <br />
                <Link to="/products">Products</Link>
            </div>
        );
    }
}