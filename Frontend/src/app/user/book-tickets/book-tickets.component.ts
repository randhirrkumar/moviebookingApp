import { Component } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { IBookTicketBody, IPayLoad, UserfuncService } from 'src/app/services/userfunc.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-book-tickets',
  templateUrl: './book-tickets.component.html',
  styleUrls: ['./book-tickets.component.css']
})
export class BookTicketsComponent {
  movie: IPayLoad | null = null;
  newTickets : number=1

  constructor(private userfunc: UserfuncService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe((params: ParamMap) => {
      console.log(params.get("id"));
      this.getMovieData(params.get("id"));
    });

  }
  getMovieData(id: any) {
    this.userfunc.getMovieById(id).subscribe((data) => {
      this.movie = data;
      console.log(data);
    })
  }

  bookMovieTickets(){
    if(!this.movie){
      return;
    }

    let movieTicket:IBookTicketBody = {
      movieIdFk:this.movie.movieId,
      noOfTicket:this.newTickets
    }
    
    this.userfunc.bookTickets(movieTicket).subscribe((data)=>{
      console.log(data);
      Swal
      .fire('Success', 'Tickets Booked Successfully !!', 'success')
      .then(() => {
        this.router.navigateByUrl('/user-dashboard');
      })

  }, (error) => {
    console.log(error);
    Swal.fire('Oops!!', 'Tickets cannot be booked', 'error');
    })
  }
}

