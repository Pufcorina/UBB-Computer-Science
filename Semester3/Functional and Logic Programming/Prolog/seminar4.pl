% Seminar 4 - Prolog

% 1. Se da o lista L.
% Sa se genereze lista tuturor aranjamentelor de K elemente din lista care au produsul P si suma S.

% initializare: c = 1
% produs(l1...ln, c) =
% 	c, n = 0
% 	produs(l2...ln, c * l1), otherwise

% produs(L:list, C:number, R:number)
% produs(i, i, o)

produs([], C, C).
produs([H|T], C, R) :-
    NC is H * C,
    produs(T, NC, R).

% initializare: c = 0
% suma(l1...ln, c) =
% 	c, n = 0
% 	suma(l2...ln, c + l1), otherwise

% suma(L:list, C:number, R:number)
% suma(i, i, o)

suma([], C, C).
suma([H|T], C, R) :-
    NC is H + C,
    suma(T, NC, R).

% inserare(l1...ln, e) =
% 	[e], n = 0
% 	e + l1...ln, n >= 1
% 	l1 + inserare(l2...ln), otherwise

% inserare(L:list, E:number, R:list)
% inserare(i, i, o)

inserare([], E, [E]).
inserare([H|T], E, [E,H|T]).
inserare([H|T], E, [H|R]) :- inserare(T, E, R).

% premutari(l1...ln) =
% 	[], n = 0
% 	inserare(permutari(l2...ln), l1), otherwise

% permutari(L:list, R:list)
% permutari(i, o)

permutari([], []).
permutari([H|T], R) :-
    permutari(T, RP),
    inserare(RP, H, R).

% combinari(l1...ln, k) =
% 	[], k = 0
% 	l1 + combinari(l2...ln, k - 1), k > 0
% 	combinari(l2...ln, k), k > 0

% combinari(L:list, K:number, R:list)
% combinari(i, i, o)

combinari(_, 0, []).
combinari([H|T], K, [H|R]) :- K > 0,
    NK is K - 1,
    combinari(T, NK, R).
combinari([_|T], K, R) :- K > 0,
    combinari(T, K, R).

% aranjamente(L:list, K:number, R:list)
% aranjamente(i, i, o)

aranjamente(L, K, R) :- 
    combinari(L, K, RC),
    permutari(RC, R).

% onesolution(L:list, K:number, S:number, P:number, R:list)
% onesolution(i, i, i, i, o)

onesolution(L, K, S, P, R) :-
    aranjamente(L, K, R),
    suma(R, 0, S),
    produs(R, 1, P).

% allsolution(L:list, K:number, S:number, P:number, R:list)
% allsolution(i, i, i, i, o)

allsolution(L, K, S, P, R) :-
    findall(RPartial, onesolution(L, K, S, P, RPartial), R). 

