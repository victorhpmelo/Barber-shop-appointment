import { AfterViewInit, Component, Input, OnChanges, OnDestroy, SimpleChanges, Output, EventEmitter, Inject, ViewChild, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { MatTimepickerModule } from '@angular/material/timepicker';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { CommonModule } from '@angular/common';
import { FormControl, FormsModule, NgForm } from '@angular/forms';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatInputModule } from '@angular/material/input';
import { Subscription } from 'rxjs';
import { SERVICES_TOKEN } from '../../../services/service.token';
import { DialogManagerService } from '../../../services/dialog-manager.service';
import { ClientScheduleAppointmentModel, SaveScheduleModel, ScheduleAppointementMonthModel, SelectClientModel } from '../../schedule.models';
import { IDialogManagerService } from '../../../services/idialog-manager.service';
import { YesNoDialogComponent } from '../../../commons/components/yes-no-dialog/yes-no-dialog.component';
import { DateAdapter, provideNativeDateAdapter } from '@angular/material/core';
@Component({
  selector: 'app-schedule-calendar',
  imports: [
    CommonModule,
    FormsModule,
    MatDatepickerModule,
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatPaginatorModule,
    MatTooltipModule,
    MatTimepickerModule,
    MatInputModule,
    MatFormFieldModule,
    MatSelectModule
  ],
  templateUrl: './schedule-calendar.component.html',
  styleUrl: './schedule-calendar.component.scss',
  providers:[
    provideNativeDateAdapter(),
    {provide: SERVICES_TOKEN.DIALOG, useClass: DialogManagerService}
  ]
})
export class ScheduleCalendarComponent implements OnInit,OnChanges, OnDestroy , AfterViewInit {
  
  private subscription?: Subscription

  private _selected: Date = new Date()

  displayedColumns: string[] = ['startAt', 'endAt', 'client', 'actions']

  dataSource!: MatTableDataSource<ClientScheduleAppointmentModel>

  addingSchedule:boolean = false

  newSchedule:SaveScheduleModel= {startAt:undefined, endAt: undefined, clientId: undefined}

  clientSelectFormControl = new FormControl()

  @Input() monthSchedule!: ScheduleAppointementMonthModel
  @Input() clients: SelectClientModel[] = []

  @Output() onDateChange = new EventEmitter<Date>()
  @Output() onConfirmDelete = new EventEmitter<ClientScheduleAppointmentModel>()
  @Output() onScheduleClient = new EventEmitter<SaveScheduleModel>()

  @ViewChild(MatPaginator) paginator!: MatPaginator

  constructor(
    @Inject(SERVICES_TOKEN.DIALOG) private readonly dialogManagerService: IDialogManagerService,
    @Inject(DateAdapter) private readonly _adapter: DateAdapter<Date>
  ) {}

  ngOnInit(): void {
    this._adapter.setLocale('pt-BR');
  }

  get selected(): Date{
    return this._selected
  }

  set selected(selected: Date){
    if(this._selected.getTime() !== selected.getTime()){
      this.onDateChange.emit(selected)
      this._selected = selected
      this.buildTable()
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
      if(changes['monthSchedule'] && this.monthSchedule){
          this.buildTable()
    }
  }
  ngOnDestroy(): void {
    if(this.subscription){
      this.subscription.unsubscribe
    }
  }
  ngAfterViewInit(): void {
    if(this.dataSource && this.paginator){
      this.dataSource.paginator = this.paginator
    }
    
  }

  onSubmit(form: NgForm): void {
    if (!this.newSchedule.startAt || !this.newSchedule.endAt || !this.newSchedule.clientId) {
      // Handle validation error
      return;
    }
  
    const startAt = new Date(this._selected);
    const endAt = new Date(this._selected);
    startAt.setHours(this.newSchedule.startAt.getHours(), this.newSchedule.startAt.getMinutes());
    endAt.setHours(this.newSchedule.endAt.getHours(), this.newSchedule.endAt.getMinutes());
  
    const saved: ClientScheduleAppointmentModel = {
      id: -1,
      day: this._selected.getDate(),
      startAt,
      endAt,
      clientId: this.newSchedule.clientId,
      clientName: this.clients.find(c => c.id === this.newSchedule.clientId)!.name
    };
  
    this.monthSchedule.scheduledAppointments.push(saved);
    this.onScheduleClient.emit(saved);
    this.buildTable();
    form.resetForm(form);
    this.newSchedule = { startAt: undefined, endAt: undefined, clientId: undefined };
  }

  private buildTable(){
    const appointments = this.monthSchedule.scheduledAppointments.filter(
      a => this.monthSchedule.year === this._selected.getFullYear() && 
      this.monthSchedule.month -1 === this._selected.getMonth() && 
      a.day == this._selected.getDate()
    )
    this.dataSource = new MatTableDataSource<ClientScheduleAppointmentModel>(appointments);
    if(this.paginator){
      this.dataSource.paginator = this.paginator
    }
  }

  requestDelete(schedule: ClientScheduleAppointmentModel){
    this.subscription = this.dialogManagerService.showYesNoDialog(
      YesNoDialogComponent, 
      {
      title: 'Excluir agendamento',
      content: `Deseja realmente excluir o agendamento do cliente ${schedule.clientName}?`
    })
    .subscribe(response => {
      if(response){
        this.onConfirmDelete.emit(schedule)
        const updatedList = this.dataSource.data.filter(s => s.id !== schedule.id)
        this.dataSource = new MatTableDataSource<ClientScheduleAppointmentModel>(updatedList)
        if(this.paginator){
          this.dataSource.paginator = this.paginator
        }
      }
    })
  }

  onTimeChange(time: Date | null): void {
    if (time) {
      const endAt = new Date(time);
      endAt.setHours(time.getHours() + 1);
      this.newSchedule.endAt = endAt;
    }
  }


}
