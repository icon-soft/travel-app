import { Travel } from './travel';
import { Bus } from './bus';
import { Ticket } from './ticket';

export class Projection {
    id: number;
    dateProjectionTravel: Date;
    prix: number;

    travel: Travel = new Travel();
    bus = new Bus();
    tickets: Array<Ticket> = new Array<Ticket>();
}
