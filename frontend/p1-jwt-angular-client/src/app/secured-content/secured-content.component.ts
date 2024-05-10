import { Component, OnInit } from '@angular/core';
import { ResourceService } from '../services/resource.service';
import { Resource } from '../interfaces/Resource';

@Component({
  selector: 'app-secured-content',
  templateUrl: './secured-content.component.html',
  styleUrls: ['./secured-content.component.css']
})
export class SecuredContentComponent implements OnInit {

    constructor(private resourceService: ResourceService) {}

    resource: Resource = {'id': 0, 'content': 'not initialized'};

    ngOnInit(): void {
        this.resourceService.getResource().subscribe({
            next: value => {
              this.resource = value;
            },
          // error: err => console.error('Observable emitted an error: ' + err),
          // complete: () => console.log('Observable emitted the complete notification')
        });
    }

}
