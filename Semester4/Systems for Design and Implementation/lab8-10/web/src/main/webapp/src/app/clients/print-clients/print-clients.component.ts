import { Component, OnInit } from '@angular/core';
import {ClientService} from '../shared/client.service';
import {ErrorComponent} from '../../error/error.component';

@Component({
  selector: 'app-print-clients',
  templateUrl: './print-clients.component.html',
  styleUrls: ['./print-clients.component.css']
})
export class PrintClientsComponent implements OnInit {
  clients: Client[];
  selectedClient: Client;

  constructor(public clientService: ClientService) { }

  ngOnInit() {
    this.getClients();
  }

  getClients(): void {
    this.clientService.getClients()
      .subscribe(clients => this.clients = clients,
        error => ErrorComponent.addError(error)
        );
  }

  onSelect(client): void {
    this.selectedClient = client;
  }
}
