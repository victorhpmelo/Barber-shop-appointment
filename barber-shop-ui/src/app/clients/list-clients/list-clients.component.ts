import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { SERVICES_TOKEN } from '../../services/service.token';
import { IClientService } from '../../services/api-client/clients/iclients.service';
import { ClientsService } from '../../services/api-client/clients/clients.service';
import { ClientTableComponent } from '../components/client-table/client-table.component';
import { SnackbarManagerService } from '../../services/snackbar-manager.service';
import { ISnackbarManagerService } from '../../services/isnackbar-manager.service';
import { Subscription } from 'rxjs';
import { ClientModelTable } from '../client.models';
import { Router } from '@angular/router';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-list-clients',
  imports: [ClientTableComponent,MatTableModule],
  templateUrl: './list-clients.component.html',
  styleUrl: './list-clients.component.scss',
  providers:[
    {provide: SERVICES_TOKEN.HTTP.CLIENT, useClass: ClientsService},
    {provide:SERVICES_TOKEN.SNACKBAR, useClass: SnackbarManagerService}
  ]

})
export class ListClientsComponent implements OnInit, OnDestroy {

  private httpSubscription:Subscription[] = []

  clients:ClientModelTable[] = []

  
  constructor( 
    @Inject(SERVICES_TOKEN.HTTP.CLIENT) private readonly httpService: IClientService,
    @Inject(SERVICES_TOKEN.SNACKBAR) private readonly snackbarManager: ISnackbarManagerService,
    private readonly router:Router
  ) { }
  ngOnInit(): void {
  this.httpSubscription.push(this.httpService.list().subscribe(data => this.clients = data))    
  }
  ngOnDestroy(): void {
    this.httpSubscription.forEach(s => s.unsubscribe())
    
  }

  delete(client:ClientModelTable){
    this.httpSubscription.push(
      this.httpService.delete(client.id).subscribe(
        _ => this.snackbarManager.show(`O cliente ${client.name} foi deletado com sucesso`))	 
    )
  }

  update(client:ClientModelTable){
    this.router.navigate([`/clients/edit-client/${client.id}`])
  }

}
