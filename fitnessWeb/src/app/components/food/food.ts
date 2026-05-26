import { ChangeDetectorRef, Component } from '@angular/core';
import { SharedModule } from '../../shared/shared-module';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../../service/user';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-food',
  imports: [SharedModule],
  templateUrl: './food.html',
  styleUrl: './food.scss',
})
export class Food {
  gridStyle = {
    width: '100%',
    textAlign: 'center',
  };

  foodForm!: FormGroup;
  foods: any[] = [];
  isSearching = false; // Folosit pentru a arăta o animație de încărcare pe butonul de Search

  constructor(
    private fb: FormBuilder,
    private message: NzMessageService,
    private userService: User,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.foodForm = this.fb.group({
      name: [null, [Validators.required]],
      calories: [null, [Validators.required]],
      protein: [null, [Validators.required]],
      carbs: [null, [Validators.required]],
      fat: [null, [Validators.required]],
      date: [null, [Validators.required]],
    });

    this.getAllFoods();
  }

  // Magia de conectare cu Edamam
  searchAPI() {
    const foodName = this.foodForm.get('name')?.value;
    
    if (!foodName) {
      this.message.warning('Te rog introdu un nume de aliment pentru a căuta!');
      return;
    }

    this.isSearching = true; // Pornim animația pe buton
    
    this.userService.searchFood(foodName).subscribe({
      next: (res) => {
        // Autocompletăm câmpurile din formular cu datele primite de la API
        this.foodForm.patchValue({
          name: res.name, // Numele frumos formatat de Edamam
          calories: res.calories,
          protein: res.protein,
          carbs: res.carbs,
          fat: res.fat
        });
        
        this.message.success('Informații nutriționale găsite și completate!');
        this.isSearching = false;
        this.cdr.markForCheck();
      },
      error: (err) => {
        this.message.error('Alimentul nu a fost găsit. Poți introduce datele manual.');
        this.isSearching = false;
        this.cdr.markForCheck();
      }
    });
  }

  submitForm(): void {
    this.userService.postFood(this.foodForm.value).subscribe({
      next: (res) => {
        this.message.success('Masa a fost salvată cu succes!', { nzDuration: 3000 });
        this.foodForm.reset();
        this.getAllFoods();
      },
      error: (err) => {
        this.message.error('Eroare la salvarea mesei', { nzDuration: 3000 });
      }
    });
  }

  getAllFoods() {
    this.userService.getFoods().subscribe(res => {
      this.foods = [...res];
      this.cdr.markForCheck();
    });
  }

  deleteFood(id: number) {
    this.userService.deleteFood(id).subscribe({
      next: (res) => {
        this.message.success("Masa a fost ștearsă din istoric!", { nzDuration: 3000 });
        this.getAllFoods();
      },
      error: (err) => {
        this.message.error("Eroare la ștergerea mesei", { nzDuration: 3000 });
      }
    });
  }
}