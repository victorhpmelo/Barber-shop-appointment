import { Component, EventEmitter, Input, NgModule, Output } from '@angular/core';
import { ClientModelForm } from '../../client.models';
import { FormsModule,NgForm, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule} from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import{MatButtonModule} from '@angular/material/button';
import { NgxMaskDirective } from 'ngx-mask';

@Component({
  selector: 'app-client-form',
  imports: [
    FormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    NgxMaskDirective

  ],
  templateUrl: './client-form.component.html',
  styleUrl: './client-form.component.scss'
})
export class ClientFormComponent {
    @Input() client:ClientModelForm = {id: 0, name: '', email: '', phone: ''};

    @Output() clientSubmitted = new EventEmitter<ClientModelForm>();

    onSubmit(_:NgForm){
      this.clientSubmitted.emit(this.client);
    }

  }
