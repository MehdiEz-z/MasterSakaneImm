export interface Reservation {
  reference?: string;
  nomClient?: string;
  prenomClient?: string;
  residence?: string;
  immeuble?: string;
  etage?: string;
  appartement?: string;
  dateReservation?: Date;
  prixMetreCarre?: number;
  prixDeVente?: number;
  statusReservation?: string;
}
