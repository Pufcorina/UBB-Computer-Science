% 2.
% a. Write a predicate to remove all occurrences of a certain atom from a list.
% b. Define a predicate to produce a list of pairs (atom n) from an initial list of atoms.
% In this initial list atom has n occurrences.
% Eg.: numberatom([1, 2, 1, 2, 1, 3, 1], X) => X = [[1, 4], [2, 2], [3, 1]].

% a

% Model matematic:
% remove_occurences(l1...ln, e) =
%	[], n = 0
%	remove_occurences(l2...ln, e), l1 = e
%	l1 + remove_occurences(l2...ln, e), l1 != e

% remove_occurences(L:list, E:number, R:list)
% remove_occurences(i, i, o)

remove_occurences([], _, []).
remove_occurences([H|T], E, R) :- H =:= E,
    remove_occurences(T, E, R).
remove_occurences([H|T], E, [H|R]) :- H =\= E,
    remove_occurences(T, E, R).

% b

% Model matematic:
% count_occurences(l1...ln, e) =
%	0, n = 0
%	1 + count_occurences(l2...ln, e), e = l1
%	count_occurences(l2...ln, e), e != l1

% count_occurences(L:list, E:number, R:number)
% count_occurences(i, i, o)

count_occurences([], _, 0).
count_occurences([H|T], E, R) :- H =:= E,
    count_occurences(T, E, R1),
    R is R1 + 1.
count_occurences([H|T], E, R) :- H =\= E,
    count_occurences(T, E, R).

% Model matematic:
% numberatom(l1...ln) =
%	[], n = 0
%	[l1, count_occurences(l1...ln, l1)] + numberatom(remove_occurences(l2...ln, l1))

% numberatom(L:list, R:list)
% numberatom(i, o)

numberatom([], []).
numberatom([H|T], [[H, RC]|R]) :-
    count_occurences([H|T], H, RC),
    remove_occurences(T, H, RR),
    numberatom(RR, R).
