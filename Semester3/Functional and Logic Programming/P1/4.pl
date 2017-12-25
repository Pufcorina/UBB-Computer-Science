% 4.
% a. Write a predicate to determine the difference of two sets.
% b. Write a predicate to add value 1 after every even element from a list.

% a

% Model matematic:
% contains(l1..ln, e) =
% 	false, n = 0
% 	true, l1 = e
% 	contains(l2...ln, e), otherwise
	
% contains(L:list, E:number)
% contains(i, i)

contains([V|_], V) :- !.
contains([_|T], V) :- contains(T, V).

% (A - B), where A and B sets
% Model matematic:
% difference(a1...an, b1...bn) =
% 	[], n = 0
% 	difference(a2...an, b1...bn), contains(b1...bn, a1) = true
% 	a1 + difference(a2...an, b1...bn), otherwise

% difference(A:list, B:list, R:list)
% difference(i, i, o)

difference([], _, []).
difference([H|T], B, R) :-
    contains(B, H),
    difference(T, B, R), !.
difference([H|T], B, [H|R]) :-
    difference(T, B, R).

% b

% Model matematic:
% insert1(l1...ln) =
% 	[], n = 0
% 	l1 + 1 + insert1(l2...ln), l1 % 2 = 0
% 	l1 + insert1(l2...ln), l1 % 2 != 0

% insert1(L:list, R:list)
% insert1(i, o)

insert1([], []).
insert1([H|T], [H, 1|R]) :- 
    H mod 2 =:= 0,
    insert1(T, R).
insert1([H|T], [H|R]) :- 
    H mod 2 =\= 0,
    insert1(T, R).