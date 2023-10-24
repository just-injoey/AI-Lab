male(pandu).
male(yudhisthir).
male(bheem).
male(arjun).
male(nakul).
male(sahadev).
male(abhimanyu).

female(kunti).
female(madri).
female(subhadra).


parent(yudhisthir, pandu).
parent(bheem, pandu).
parent(arjun, pandu).
parent(nakul, pandu).
parent(sahadev, pandu).
parent(yudhisthir, kunti).
parent(bheem, kunti).
parent(arjun, kunti).
parent(nakul, madri).
parent(sahadev, madri).
parent(abhimanyu,arjun).
parent(abhimanyu, subhadra).

grand_father(X,Y):-
    parent(X,Z),
    parent(Z,Y),
    male(Y).
grand_mother(X,Y):-
    parent(X,Z),
    parent(Z,Y),
    female(Y).
