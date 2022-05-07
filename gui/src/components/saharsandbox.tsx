import React from 'react';
// import './App.scss';
// import {LikeClicker} from "./LikeClicker/LikeClicker";
//
// export type Ticket = {
//     id: string,
//     title: string;
//     content: string;
//     creationTime: number;
//     userEmail: string;
//     labels?: string[];
//     pinTicket?: boolean;
//     seeMore: boolean;
//     like: boolean;
// }
//
// export type AppState = {
//     tickets?: Ticket[],
//     search: string,
//     page: number,
//     dataLeft: boolean
//     favoriteTicketButton: boolean
// }
//
// const data = data.json;
//
// export class App extends React.PureComponent<{}, AppState> {
//
//     state: AppState = {
//         search: '',
//         tickets: [],
//         page: 1,
//         dataLeft: true,
//         favoriteTicketButton: false
//     }
//
//     searchDebounce: any = null;
//
//     async componentDidMount() {
//         this.setState({
//             tickets: await data.getTickets(this.state.page,this.state.search),
//             dataLeft: await data.dataLeft(this.state.page , this.state.search)
//         });
//     }
//
//
//     renderTickets = (tickets: Ticket[], favoriteTicketButton: boolean) => {
//         const filteredTickets = filterT()
//
//         // this is like filter, he shows on screen only the tickets the user mark as 'liked'
//         function filterT(){
//             if(favoriteTicketButton){
//                 return tickets.filter((t) => (t.like));
//             }
//             else{
//                 return tickets
//             }
//         }
//
//
//         const pinTicketFunc = async (ticket : Ticket) => {
//             ticket.pinTicket =! ticket.pinTicket;
//             const index = tickets.indexOf(ticket);
//             const tmpTicket = tickets.splice(index, 1);
//             tickets.unshift(tmpTicket[0]);
//             this.setState({tickets:[...tickets]});
//         }
//
//         function pinStatus(ticket : Ticket){
//             if(ticket.pinTicket){
//                 return "Unpin"
//             }
//             else{
//                 return "Pin"
//             }
//         }
//
//         // this function determine if the ticket has long content or not.
//         const hasLongText = (ticket : Ticket) => {
//             if (ticket.content.length >= 420){
//                 return true
//             }
//             else{
//                 return false
//             }
//         }
//
//         const revelMore = (ticket : any) =>{
//             ticket.seeMore = true;
//             this.setState({tickets:[...tickets]})
//         }
//
//         const revelLess = (ticket : any) =>{
//             ticket.seeMore = false;
//             this.setState({tickets:[...tickets]})
//         }
//
//         // this function affect on the button content: see more/ see less.
//         function seeMoreLess(ticket : Ticket){
//             if(!ticket.seeMore){
//                 return "See more"
//             }
//             else{
//                 return "See less"
//             }
//         }
//
//
//         // @ts-ignore
//         // @ts-ignore
//         return (<ul className='tickets'>
//             {filteredTickets.map((ticket,index) => (<li key={ticket.id} className='ticket'>
//
//                 <div className='topTicket'>
//                     <div>
//                         <h5 className='title'>{ticket.title}</h5>
//                     </div>
//                     <div className='buttons'>
//                         {/* eslint-disable-next-line react/jsx-no-undef */}
//                         <LikeClicker like={ticket.like} handleClick={
//                             () => {
//                                 let newTickets = [...tickets];
//                                 newTickets[index].like = !newTickets[index].like;
//                                 this.setState({tickets: newTickets})
//                             }
//                         } />
//                         <button className='pinButton' onClick={() => pinTicketFunc(ticket)}>{pinStatus(ticket)}</button>
//                     </div>
//                 </div>
//
//                 <div className='content'>{
//                     !hasLongText(ticket) ? <p>{ticket.content}</p> :
//                         !ticket.seeMore ? <div><div className='longText'> {ticket.content}</div>
//                                 <a onClick={() => revelMore(ticket)}>{seeMoreLess(ticket)}</a></div>
//                             : <div>{ticket.content}<div><a onClick={() => revelLess(ticket)}>{seeMoreLess(ticket)}</a></div></div>
//                 }</div>
//
//                 <div className="ticket-footer">
//                     <div className='meta-data'>By {ticket.userEmail} | { new Date(ticket.creationTime).toLocaleString()}</div>
//                     <div>
//                         {ticket.labels && ticket.labels.map((label) => <span className='tLabel'>{label}</span>)}
//                     </div>
//
//                 </div>
//
//
//
//             </li>))}
//         </ul>);
//     }
//
//
//     onSearch = async (val: string, newPage?: number) => {
//
//         clearTimeout(this.searchDebounce);
//
//         this.searchDebounce = setTimeout(async () => {
//             this.setState({search: val});
//             const newTickets = await data.getTickets(this.state.page,val);
//             this.setState({tickets: newTickets});
//
//         }, 300);
//     }
//
//
//     loadMore = async () =>{
//         const tickets = await data.getTickets(this.state.page + 1, this.state.search);
//         const dataLeft = await data.dataLeft(this.state.page + 1 , this.state.search);
//         this.setState({
//             dataLeft : dataLeft,
//             tickets: [...this.state.tickets!, ...tickets],
//             page: this.state.page + 1
//         });
//     }
//
//     // this function show on screen only the liked ticket
//     filterTicketByLike(){
//         const newState = !this.state.favoriteTicketButton;
//         this.setState({favoriteTicketButton: newState});
//     }
//
//     render() {
//         const {tickets} = this.state;
//
//         return (<main>
//             <div className='topOfPage'>
//                 <div>
//                     <h1>Tickets List</h1>
//                 </div>
//                 <div>
//                     <button className='likeButtonFilter' onClick={() => this.filterTicketByLike()}>Favorite tickets</button>
//                 </div>
//             </div>
//
//             <header>
//                 <input type="search" placeholder="Search..." onChange={(e) => this.onSearch(e.target.value)}/>
//             </header>
//             {tickets ? <div className='results'>Showing {tickets.length} results</div> : null }
//             {tickets ? this.renderTickets(tickets,this.state.favoriteTicketButton) : <h2>Loading..</h2>}
//
//             <div className='loadB'>
//                 {this.state.dataLeft ? <button className='loadMoreTickets' onClick={this.loadMore}>Load more tickets</button> : null}
//             </div>
//         </main>)
//     }
// }
//
// export default App;
// @ts-ignore
export default class Login {
}