import React from "react"
import bookStore from "../stores/bookStore";

export default class NewBook extends React.Component{
  constructor(){
    super();
    this.state = {book:{ title: "",info: "", moreInfo: ""}};
  }
  
  saveBook = () =>{
   bookStore.addBook(this.state.book);
   //Clear input fields, to allow for a new "new book"
   this.setState({book:{ title: "",info: "", moreInfo: ""}});
   event.preventDefault();//?
  }

  handleChange = (event) => {
    var book = this.state.book;
    var id = event.target.id;
    if(id === "title"){
      book.title = event.target.value;
    }
    if(id === "info"){
      book.info = event.target.value;
    }
    if(id === "moreInfo"){
      book.moreInfo = event.target.value;
    }
    this.setState({book});  
  }

  render(){
    return (
      <div>
      <h2>New Book </h2>
      <form onSubmit={this.saveBook}>
       <input onChange={this.handleChange} value={this.state.book.title} id="title" type="text" placeholder="title"/><br/>
       <input onChange={this.handleChange} value={this.state.book.info} id="info" type="text" placeholder="info"/><br/>
       <input onChange={this.handleChange} value={this.state.book.moreInfo} id="moreInfo" type="text" placeholder="moreInfo"/><br/><br/>
       <button className="btn btn-sm btn-success" 
       >Save Book </button>
       <p> {JSON.stringify(this.state.book)}</p>
      </form>
      </div>
    )
  }

}