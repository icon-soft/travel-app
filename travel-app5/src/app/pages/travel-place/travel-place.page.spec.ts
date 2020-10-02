import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { TravelPlacePage } from './travel-place.page';

describe('TravelPlacePage', () => {
  let component: TravelPlacePage;
  let fixture: ComponentFixture<TravelPlacePage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TravelPlacePage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(TravelPlacePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
