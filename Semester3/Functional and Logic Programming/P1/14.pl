% 14.
% a. Write a predicate to test equality of two sets without using the set difference.
% b. Write a predicate to select the n-th element of a given list.

% a

% Model matematic:
% my_length(l1...ln) =
% 	0, n = 0
% 	1 + my_length(l2...ln), otherwise

% my_length(L -list, N - number)
% my_length(i, o)
my_length([], 0).
my_length([_|T], C):- my_length(T, C1),
    C is C1 + 1.

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
% remove_occurences(l1...ln, e) =
%    [], n = 0
%    l1 + remove_occurences(l2...ln, e), l1 != e
%    remove_occurences(l2...ln, e), l1 = e

% remove_occurences(L:list, E:number, R:list)
% remove_occurences(i, i, o)

remove_occurences([], _, []).
remove_occurences([H|T], E, R) :- H =:= E,
    remove_occurences(T, E, R).
remove_occurences([H|T], E, [H|R]) :- H =\= E,
    remove_occurences(T, E, R).

% Model matematic:
% set_equal(a1...an, b1...bm) =
% 	true, n = 0 and m = 0
% 	set_equal(remove_occurences(a2...an, b1), remove_occurences(b2...bm, a1)), n = m and
% 						contains(a1...an, b1) = true and contains(b1...bm, a1) = true

% set_equal(A:list, B:list)
% set_equal(i, i)

set_equal([], []).
set_equal([HA|TA], [HB|TB]) :-
    my_length(TA, CA),
    my_length(TB, CB),
    CA =:= CB,
    contains([HB|TB], HA),
    contains([HA|TA], HB),
    remove_occurences(TB, HA, RA),
    remove_occurences(TA, HB, RB),
    set_equal(RA, RB).

% b

% Model matematic:
% select_n(l1...ln, n) =
% 	l1, n = 1
% 	select_n(l1...ln, n - 1), otherwise

% select_n(L:list, N:number, R:number)
% select_n(i, i, o)

select_n([H|_], 1, H) :- !.
select_n([_|T], N, R) :- 
    New_n is N - 1,
    select_n(T, New_n, R).