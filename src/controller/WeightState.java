//
//pb_id	rb_id	tol 	netto	opr_id
//1	1	0.5	10.05	1 // Dej
//1	2	0.5	2.03	1 // Tomat
//1	4	0.5	1.98	1 // Tomat
//2	1	0.5	10.01	2 // Dej
//2	2	0.5	1.99	2 // Tomat
//2	5	0.5	1.47	2 // Ost
//3	1	0.5	10.07	1 // Dej
//3	3	0.5	2.06	2 // Tomat
//3	4	0.5	1.55	1 // Tomat
//3	6	0.5	1.53	2 // Ost
//4	1	0.5	10.02	3 // Dej
//4	5	0.5	1.57	3 // Ost
//4	6	0.5	1.03	3 // Ost
//4	7	0.5	0.99	3 // Skinke
//
//
//
//
//l.add(new RaavareBatchDTO(1, 1, leverandoer.get(0), 1000)); // Dej
//l.add(new RaavareBatchDTO(2, 2, leverandoer.get(1), 300));  // Tomat
//l.add(new RaavareBatchDTO(3, 2, leverandoer.get(2), 300));  // Tomat
//l.add(new RaavareBatchDTO(4, 2, leverandoer.get(3), 125));  // Tomat
//l.add(new RaavareBatchDTO(5, 3, leverandoer.get(4), 250));  // Ost
//l.add(new RaavareBatchDTO(6, 3, leverandoer.get(5), 150));  // Ost
//l.add(new RaavareBatchDTO(7, 4, leverandoer.get(5), 650));  // Skinke
//l.add(new RaavareBatchDTO(8, 5, leverandoer.get(6), 350));  // Champignon
//l.add(new RaavareBatchDTO(9, 6, leverandoer.get(6), 200));  // Ananas
//l.add(new RaavareBatchDTO(10, 7, leverandoer.get(6), 50));  // Atiskokker
//
//// Margherita
//ArrayList<ReceptKompDTO> komplist1 = new ArrayList<ReceptKompDTO>();
//komplist1.add(new ReceptKompDTO(dao.getRaavare(1), 10, 0.1)); // dej
//komplist1.add(new ReceptKompDTO(dao.getRaavare(2), 2, 0.1)); // tomat
//komplist1.add(new ReceptKompDTO(dao.getRaavare(3), 2, 0.1)); // ost
//komplist1.add(new ReceptKompDTO(dao.getRaavare(5), 2, 0.1)); // champignon
//l.add(new ReceptDTO(1, "Margherita", komplist1));
//
//	komplist.add(new ProduktBatchKompDTO(1, 1, 10.9, 1));
//	komplist.add(new ProduktBatchKompDTO(1, 3, 2.01, 1));
//	komplist.add(new ProduktBatchKompDTO(1, 5, 1.98, 1));
//	komplist.add(new ProduktBatchKompDTO(1, 8, 2.05, 1));
//	new ProduktBatchDTO(1, COMPLETED, 1, komplist);
//	komplist.clear();
//
//	komplist.add(new ProduktBatchKompDTO(2, 1, 10.9, 2));
//	komplist.add(new ProduktBatchKompDTO(2, 1, 2.01, 2));
//	komplist.add(new ProduktBatchKompDTO(2, 4, 1.98, 2));
//	komplist.add(new ProduktBatchKompDTO(2, 8, 2.05, 2));
//	new ProduktBatchDTO(2, COMPLETED, 1, komplist);
//	komplist.clear();
//
//
//// ProduktBatchKompDTO(pbId, rbId, netto, oprId)
//// ProduktBatchDTO(pbId, status, receptId, list)
//
//// Prosciutto
//ArrayList<ReceptKompDTO> komplist2 = new ArrayList<ReceptKompDTO>();
//komplist2.add(new ReceptKompDTO(dao.getRaavare(1), 10, 0.1)); // dej
//komplist2.add(new ReceptKompDTO(dao.getRaavare(2), 2, 0.1)); // tomat
//komplist2.add(new ReceptKompDTO(dao.getRaavare(3), 2, 0.1)); // ost
//komplist2.add(new ReceptKompDTO(dao.getRaavare(4), 1.5, 0.1)); // skinke
//l.add(new ReceptDTO(2, "Prosciutto", komplist2));
//
//	komplist.add(new ProduktBatchKompDTO(3, 1, 10.9, 1));
//	komplist.add(new ProduktBatchKompDTO(3, 1, 2.01, 1));
//	komplist.add(new ProduktBatchKompDTO(3, 4, 1.98, 1));
//	komplist.add(new ProduktBatchKompDTO(3, 8, 2.05, 1));
//	new ProduktBatchDTO(3, COMPLETED, 2, komplist);
//	komplist.clear();
//
//// Capricciosa
//ArrayList<ReceptKompDTO> komplist3 = new ArrayList<ReceptKompDTO>();
//komplist3.add(new ReceptKompDTO(dao.getRaavare(1), 10, 0.1)); // dej
//komplist3.add(new ReceptKompDTO(dao.getRaavare(2), 2, 0.1)); // tomat
//komplist3.add(new ReceptKompDTO(dao.getRaavare(3), 2, 0.1)); // ost
//komplist3.add(new ReceptKompDTO(dao.getRaavare(4), 1.5, 0.1)); // skinke
//komplist3.add(new ReceptKompDTO(dao.getRaavare(5), 1.5, 0.1)); // champignon
//komplist3.add(new ReceptKompDTO(dao.getRaavare(7), 1.5, 0.1)); // artichoke
//l.add(new ReceptDTO(3, "capricciosa", komplist3));
//
//// Hawaii
//ArrayList<ReceptKompDTO> komplist4 = new ArrayList<ReceptKompDTO>();
//komplist4.add(new ReceptKompDTO(dao.getRaavare(1), 10, 0.1)); // dej
//komplist4.add(new ReceptKompDTO(dao.getRaavare(2), 2, 0.1)); // tomat
//komplist4.add(new ReceptKompDTO(dao.getRaavare(3), 2, 0.1)); // ost
//komplist4.add(new ReceptKompDTO(dao.getRaavare(4), 1.5, 0.1)); // skinke
//komplist4.add(new ReceptKompDTO(dao.getRaavare(6), 1.5, 0.1)); // ananas
//l.add(new ReceptDTO(4, "Hawaii", komplist4));