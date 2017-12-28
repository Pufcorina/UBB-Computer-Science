% Seminar 3 - Prolog

% 1. Numarul de aparitii al unui element intr-o lista, folosind metoda variabilei colectoare.

% nOcc(l1...ln, e, c) =
% 	c, n = 0
% 	nOcc(l2...ln, e, c + 1), l1 = e
% 	nOcc(l2...ln, e, c), l1 != e

% nOcc(L:list, E:number, C:number, R:number)
% nOcc(i, i, i, o)

nOcc([], _, C, C).
nOcc([H|T], E, C, R) :- H =:= E,
    NC is C + 1,
    nOcc(T, E, NC, R).
nOcc([H|T], E, C, R) :- H =\= E,
    nOcc(T, E, C, R).

% 2. Inversarea unui numar, folosind metoda variabilei colectoare.

% inv(x, c) =
% 	c, x = 0
% 	inv(x / 10, c * 10 + x % 10), otherwise

% inv(E:number, C:number, R:number)
% int(i, i, o)

inv(0, C, C).
inv(E, C, R) :- E =\= 0,
    UC is E mod 10,
    NE is (E - UC)/ 10,
    NC is C * 10 + UC,
    inv(NE, NC, R).

% 3. Se da o lista de numere si liste de numere. 
% Se cere ca din fiecare sublista sa se stearga numerele palindroame.
% L = [[2, 33, 122], 22, 33, 17, [121, 43, 575], [535]] =>
% 	R = [[122], 22, 33, 17, [43], []]

% delSublist(l1...ln) =
% 	[], n = 0
% 	l1 + delSublist(l2...ln), inv(l1, 0) != l1
% 	delSublist(l2...ln), inv(l1, 0) = l1

% delSublist(L:list, R:list)
% delSublist(i, o)

delSublist([], []).
delSublist([H|T], [H|R]) :-
    inv(H, 0, RC),
    RC =\= H, !,
    delSublist(T, R).
delSublist([_|T], R) :-
    delSublist(T, R).

% mainHeter(l1...ln) =
% 	[], n = 0
% 	l1 + mainHeter(l2...ln), number(l1) = True
% 	delSublist(l1) + mainHeter(l2...ln), is_list(l1) = True

% mainHeter(L:list, R:list)
% mainHeter(i, o)

mainHeter([], []).
mainHeter([H|T], [H|R]) :- number(H),
    mainHeter(T, R).
mainHeter([H|T], [HR|R]) :- is_list(H),
    delSublist(H, HR),
    mainHeter(T, R).

% 4. Se da o lista eterogena, formata din numere ?i liste de numere. 
% Se cere sa se determine numarul sublistelor care au aspect de munte. 
% (O lista are aspect de munte daca este alcatuita dintr-o secven?a de numere crescatoare, 
% urmata de o secven?a de numere descrescatoare. 
% Orice alta varianta (doar secven?a crescatoare, doar secven?a descrescatoare, 
% secven?a care descre?te ?i dupa aceea cre?te, etc.) nu are aspect de munte.)
% De exemplu: [1,2,[1,2,3,2], 6,[1,2],[1,4,5,6,7,1],8,2,[4,3,1],11,5,[6,7,6],8] 
% =>3 (sublistele [1,2,3,2], [1,4,5,6,7,1] ?i [6,7,6]).

% munte(l1...ln, f) =
% 	true, n <= 1 and f = 1
% 	munte(l2...ln, 0), l1 < l2 and f = 0
% 	munte(l2...ln, 1), l1 >= l2 and f = 0
% 	munte(l2...ln, 1, l1 > l2 and f = 1
% 	false, otherwise

munte([], 1).
munte([_], 1).
munte([H1, H2|T], 0) :- H1 < H2,
    munte([H2|T], 0).
munte([H1, H2|T], 0) :- H1 >= H2,
    munte([H2|T], 1).
munte([H1, H2|T], 1) :- H1 > H2,
    munte([H2|T], 1).

% isMountain(l1...ln) =
% 	munte(l1...ln, 0), l1 < l2

% isMountain(L:list)
% isMountain(i)

isMountain([H1, H2, H3|T]) :- H1 < H2, munte([H1, H2, H3|T] ,0).

% nrSubliste(l1...ln, c) =
% 	c, n = 0
% 	nrSubliste(l2...ln, c + 1), is_list(l1) and isMountain(l1)
% 	nrSubliste(l2...ln, c), otherwise

% nrSubliste(L:list, C:number, R:number)
% nrSubliste(i, i, o)

nrSubliste([], C, C).
nrSubliste([H|T], C, R) :- 
    is_list(H),
    isMountain(H), !,
    NC is C + 1,
    nrSubliste(T, NC, R).
nrSubliste([_|T], C, R) :-
    nrSubliste(T, C, R).
    
