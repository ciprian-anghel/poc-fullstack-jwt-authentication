import { Component, OnDestroy, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ResourceService } from './services/resource.service';
import { Subscription } from 'rxjs';
import { Resource } from './interfaces/Resource';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  title = 'Main Page';

  resource: Resource = {id: -1, content: 'Resource not loaded'};
  resourceSubscription!: Subscription;
  
  constructor(private http: HttpClient, private resourceService: ResourceService) { }

  ngOnInit(): void {
    this.getResource();
  }

  getResource(): void {
    this.resourceSubscription = this.resourceService.getResource().subscribe({
      next: data => this.resource = data,
      error: err => console.error('Observable emitted an error: ' + err),
      complete: () => console.log('Observable emitted the complete notification')
    })
  }
  
  ngOnDestroy(): void {
    // Unsubscribe from the observable to prevent memory leaks
    if (this.resourceSubscription) {
      this.resourceSubscription.unsubscribe();
    }
  }
  
} 