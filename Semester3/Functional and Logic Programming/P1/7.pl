% 7.
% a. Write a predicate to compute the intersection of two sets.
% b. Write a predicate to create a list (m, ..., n) of all integer numbers from the interval [m, n].

% a

% Model matematic:
% contains(l1...ln, e) =
% 	false, n = 0
% 	true, l1 = e
%	contains(l2...ln, e), otherwise

% contains(L:list, E:number)
% contains(i, i)

contains([V|_], V) :- !.
contains([_|T], E) :- contains(T, E).

% Model matematic:
% intersection(a1...an, b1...bm) =
% 	[], n = 0
% 	a1 + intersection(a2...an, b1...bm), contains(b1...bm, a1) = true
% 	intersection(a2...an, b1...bm), otherwise

% intersection(A:list, B:list, R:list)
% intersection(i, i, o)

intersection([], _, []).
intersection([H|T], B, [H|R]) :-
    contains(B, H),
    intersection(T, B, R), !.
intersection([_|T], B, R) :- intersection(T, B, R).

% b

% Model matematic:
% new_list(m, n) =
% 	[], m = n
% 	m + new_list(m + 1, n)

% new_list(M:number, N:number, R:list)
% new_list(i, i, o)
 
new_list(N, N, [N]).
new_list(M, N, [M|R]) :- 
    New_m is M + 1,
    new_list(New_m, N, R).