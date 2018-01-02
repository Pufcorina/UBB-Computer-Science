% 6. Generate the list of all arrangements of K elements of a given list.
% Eg: [2, 3, 4] K=2 => [[2,3], [3,2], [2,4], [4,2], [3,4], [4,3]] (not necessary in this order)

% inserare(l1...ln, e) =
% 	[e], n = 0
% 	e + l1...ln, n >= 1
% 	l1 + inserare(l2...ln, e), otherwise

% inserare(L:list, E:number, R:list)
% inserare(i, i, o)

inserare([], E, [E]).
inserare([H|T], E, [E,H|T]).
inserare([H|T], E, [H|R]) :- 
         inserare(T, E, R).

% permutari(l1...ln) =
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
% combinari(i, o)

combinari(_, 0, []).
combinari([H|T], K, [H|R]) :-
    K > 0,
    NK is K - 1,
    combinari(T, NK, R).
combinari([_|T], K, R) :-
    K > 0,
    combinari(T, K, R).

% aranjamente(L:list, K:number, R:list)
% aranjamente(i, i, o)

aranjamente(L, K, R) :-
    combinari(L, K, RC),
    permutari(RC, R).

% allsolutions(L:list, N:number, R:list)
% allsolutions(i, i, o)

allsolutions(L, N, R) :-
    findall(RPartial, aranjamente(L, N, RPartial), R).