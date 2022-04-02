import { Component, OnInit } from '@angular/core';
import { IngredientesEditables } from '../model/pizzaiolo/ingredientesEditables';
import { IngredientesService } from '../services/ingredientes.service';

interface tipoIngrediente {
  name: string,
  code: string
}

@Component({
  selector: 'app-ingredientes',
  templateUrl: './ingredientes.component.html',

  styleUrls: ['./ingredientes.component.scss'],
}
)

export class IngredientesComponent implements OnInit {

  ingredienteDialog: boolean = false;
  ingredientes: any = [];
  ingrediente: IngredientesEditables = { idIngredient: -1};
  submitted: boolean = false;

  constructor(public restApi: IngredientesService) {  }

  ngOnInit(): void {
    this.restApi.getIngredientes().subscribe(
        ( data: {} ) => {
          this.ingredientes = data
          console.log(this.ingredientes)
        }
      );
  }

  openNew() {
    this.ingrediente = {idIngredient: 0};
    this.submitted = false;
    this.ingredienteDialog = true;
  }

  openEdit(ingrediente: IngredientesEditables) {
    this.ingrediente = { ...ingrediente }
    this.ingredienteDialog = true;

  }

  saveIngrediente(){

    if( this.ingrediente.name?.trim() !== ''  ){
      if( this.ingrediente.idIngredient > 0 ){
        this.restApi.updateIngrediente(this.ingrediente.idIngredient, this.ingrediente).subscribe(
          (data: {}) => {
            this.restApi.getIngredientes().subscribe(
              ( data: {} ) => {
                this.ingredientes = data
                console.log(this.ingredientes)
              }
            );
          }
        )
      }else{
        //createIngrediente
        this.restApi.createIngrediente(this.ingrediente).subscribe(
          (data: {}) => {
            this.restApi.getIngredientes().subscribe(
              ( data: {} ) => {
                this.ingredientes = data
                console.log(this.ingredientes)
              }
            );
          }
        )
      }
    }

    this.ingredienteDialog = false;
    this.submitted = false;
  }

  hideDialog() {
    this.ingredienteDialog = false;
    this.submitted = false;
  }

}
