import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class ClientService {
  path = 'http://localhost:8080/api/clients';

  constructor(public httpClient: HttpClient) { }

  getClients(): Observable<Array<Client>> {
    console.log('getClients() enter ');
    const a = this.httpClient.get<Array<Client>>(this.path);
    console.log('getClients() exit ' + a);
    return a;
  }

  getClient(clientId): Observable<Client> {
    console.log('getClient() enter clientId ' + clientId);
    const a = this.httpClient.get<Client>(this.path + '/' + clientId);
    console.log('getClient() exit ' + a);
    return a;
  }

}
