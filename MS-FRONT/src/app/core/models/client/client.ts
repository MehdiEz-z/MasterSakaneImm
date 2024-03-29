export interface Client {
  reference?: string;
  nom?: string;
  prenom?: string;
  dateNaissance?: Date;
  lieuNaissance?: string;
  nomPere?: string;
  nomMere?: string;
  adresse?: string;
  cin?: string;
  cinValidite?: Date;
  telephone?: string;
  nationalite?: string;
  email?: string;
  profession?: string;
  situationFamiliale?: string;
  methode?: string;
}
